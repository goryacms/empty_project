package ru.bellintegrator.practice.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.guides.dao.CitizenshipDAO;
import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.dao.DocUserDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.guides.view.DocUserView;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.service.UserValidService;
import ru.bellintegrator.practice.users.view.UserView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO dao;
    private final OfficeDAO daoOffice;
    private final CitizenshipDAO daoCitizenship;
    private final DocDAO daoDoc;
    private final DocUserDAO daoDocUser;

    private UserValidService userValidService;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");

    @Autowired
    public UserServiceImpl(UserDAO dao, OfficeDAO daoOffice, CitizenshipDAO daoCitizenship,
                           DocDAO daoDoc, DocUserDAO daoDocUser, UserValidService userValidService) {
        this.dao = dao;
        this.daoOffice = daoOffice;
        this.daoCitizenship = daoCitizenship;
        this.daoDoc = daoDoc;
        this.daoDocUser = daoDocUser;

        this.userValidService = userValidService;
    }

    @Override
    @Transactional
    public List<User> all() {
        List<User> all = dao.all();
        return all;
    }

    @Override
    @Transactional
    public List<UserView> loadByParams(UserView user) {
        userValidService.checkList(user);

        User us = new User();

        Office office = daoOffice.loadById(user.officeId);
        us.setOffice(office);

        us.setFirstName(user.firstName);
        us.setLastName(user.secondName);
        us.setMiddleName(user.middleName);
        us.setPosition(user.position);

        if(user.citizenshipCode != null) {
            Citizenship citizen = daoCitizenship.loadByCode(user.citizenshipCode);
            us.setCitizenship(citizen);
        }

        List<User> all = dao.loadByParams(us);

        if(all.size() == 0) {
            throw new ResourceNotFoundException("Информация по заданным условиям не найдена");
        }

        Function<User, UserView> mapUser = p -> {
            UserView view = new UserView();
            view.id         = p.getId();
            view.fullName  = p.getOffice().getOrganization().getFullName();
            view.position = p.getPosition();
            view.officeName = p.getOffice().getName();

            return view;
        };

        return all.stream()
                .map(mapUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserView loadById(Long id) {
        User us = this.dao.loadById(id);

        if(us == null) {
            throw new ResourceNotFoundException("Пользователь с идентификатором = " + id + " не найден");
        }

        UserView view = new UserView();
        view.id = us.getId();
        view.officeId = us.getOffice().getId();
        view.firstName = us.getFirstName();
        view.secondName = us.getLastName();


        view.middleName = us.getMiddleName();
        view.position = us.getPosition();

        view.salary = us.getSalary();


        view.registrationDate = String.valueOf(us.getRegistrationDate());
        view.phone = us.getPhone();

        DocUser docUser = us.getDocUser();

        view.docCode = docUser.getDoc().getId();
        view.docName = docUser.getDoc().getName();
        view.docNumber = docUser.getDocNumber();
        view.docDate = String.valueOf(docUser.getDocDate());


        Citizenship citiz = us.getCitizenship();
        view.citizenshipCode = citiz.getCode();
        view.citizenshipName = citiz.getName();

        return view;
    }

    @Override
    @Transactional
    public UserView save(UserView user) {
        userValidService.checkSave(user);

        User us = new User();

        Office office = daoOffice.loadById(user.officeId);
        us.setOffice(office);

        us.setFirstName(user.firstName);
        us.setMiddleName(user.middleName);
        us.setLastName(user.secondName);
        us.setPosition(user.position);

        Double sal = user.salary != null ? user.salary : 0.00;
        us.setSalary(sal);

        us.setRegistrationDate(getDate(user.registrationDate));
        us.setPhone(user.phone);


        DocUser docUser = new DocUser();

        docUser.setDocDate(getDate(user.docDate));
        docUser.setDocNumber(user.docNumber);

        Doc doc = daoDoc.loadById(user.docCode);
        docUser.setDoc(doc);

        docUser.setUser(us);

        us.setDocUser(docUser);


        Citizenship citizen = daoCitizenship.loadByCode(user.citizenshipCode);
        us.setCitizenship(citizen);

        this.dao.save(us);

        UserView view = new UserView();
        view.id = us.getId();
        return view;
    }

    @Override
    @Transactional
    public UserView update(UserView user) {
        userValidService.checkUpdate(user);

        User us = new User();

        us.setId(user.id);
        us.setFirstName(user.firstName);
        us.setMiddleName(user.middleName);
        us.setLastName(user.secondName);
        us.setPosition(user.position);

        if(user.salary != null)
            us.setSalary(user.salary);


        us.setRegistrationDate(getDate(user.registrationDate));
        us.setPhone(user.phone);

        // Загрузка документов
        DocUserView docUserView = new DocUserView();
        docUserView.docId = user.docCode;
        docUserView.userId = user.id;


        DocUser docUser = (DocUser) daoDocUser.loadByParams(docUserView);
        docUser.setDocDate(getDate(user.docDate));
        docUser.setDocNumber(user.docNumber);

        Doc doc = daoDoc.loadById(user.docCode);

        docUser.setDoc(doc);

        us.setDocUser(docUser);

        Citizenship citizen = daoCitizenship.loadByCode(user.citizenshipCode);
        us.setCitizenship(citizen);

        this.dao.update(us);

        UserView view = new UserView();
        view.result = "success";
        return view;
    }

    @Override
    @Transactional
    public UserView delete(UserView user) {
        this.dao.delete(user.id);

        UserView view = new UserView();
        view.result = "success";
        return view;
    }

    /**
     *
     * Парсинг даты
     */
    private Date getDate(String strDate){
        Date outDate = null;
        try {
            outDate = format.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        return outDate;
    }
}
