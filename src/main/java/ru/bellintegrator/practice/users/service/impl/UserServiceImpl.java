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
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.view.UserView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {
    private final UserDAO dao;
    private final OfficeDAO daoOffice;
    private final CitizenshipDAO daoCitizenship;
    private final DocDAO daoDoc;
    private final DocUserDAO daoDocUser;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");

    @Autowired
    public UserServiceImpl(UserDAO dao, OfficeDAO daoOffice, CitizenshipDAO daoCitizenship,
                           DocDAO daoDoc, DocUserDAO daoDocUser) {
        this.dao = dao;
        this.daoOffice = daoOffice;
        this.daoCitizenship = daoCitizenship;
        this.daoDoc = daoDoc;
        this.daoDocUser = daoDocUser;
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
        User us = new User();

        Office office = daoOffice.loadById(user.officeId);
        us.setOffice(office);

        us.setFirstName(user.firstName);
        us.setLastName(user.secondName);
        us.setMiddleName(user.middleName);
        us.setPosition(user.position);

        Citizenship citizen = daoCitizenship.loadByCode(user.citizenshipCode);

        us.setCitizenship(citizen);

        List<User> all = dao.loadByParams(us);

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


        // По заданию нужно вывести 1 документ, у меня связь М:М. Поэтому беру первую запись.
        // Если такая реализация ошибочна, могу убрать связь М:М или вывести массив документов
        Optional <DocUser> first = us.getDocUsers().stream().findFirst();
        view.docCode = first.get().getDocs().getId();
        view.docName = first.get().getDocs().getName();
        view.docNumber = first.get().getDocNumber();
        view.docDate = String.valueOf(first.get().getDocDate());

        Citizenship citiz = us.getCitizenship();
        view.citizenshipCode = citiz.getCode();
        view.citizenshipName = citiz.getName();

        return view;
    }

    @Override
    @Transactional
    public void save(UserView user) {
        User us = new User();

        Office office = daoOffice.loadById(user.officeId);
        us.setOffice(office);

        us.setFirstName(user.firstName);
        us.setMiddleName(user.middleName);
        us.setLastName(user.secondName);
        us.setPosition(user.position);
        us.setSalary(user.salary);

        /**
         * TODO: user.registrationDate --> nullPointerException
         */
        us.setRegistrationDate(getDate(user.registrationDate));
        us.setPhone(user.phone);

        DocUser docUser = new DocUser();

        /**
         * TODO: user.docDate --> nullPointerException
         */
        docUser.setDocDate(getDate(user.docDate));

        docUser.setDocNumber(user.docNumber);

        Doc doc = daoDoc.loadById(user.docCode);
        docUser.setDocs(doc);

        /**
         * TODO: user.citizenshipCode --> nullPointerException
         */
        Citizenship citizen = daoCitizenship.loadByCode(user.citizenshipCode);
        us.setCitizenship(citizen);

        this.dao.save(us);
    }

    @Override
    @Transactional
    public void update(UserView user) {
        User us = new User();

        us.setId(user.id);
        us.setFirstName(user.firstName);
        us.setMiddleName(user.middleName);
        us.setLastName(user.secondName);
        us.setPosition(user.position);
        us.setSalary(user.salary);


        us.setRegistrationDate(getDate(user.registrationDate));
        us.setPhone(user.phone);

        // Загрузка документов
        DocUser docUser = (DocUser) daoDocUser.loadByParams( user.docCode,user.id);
        docUser.setDocDate(getDate(user.docDate));
        docUser.setDocNumber(user.docNumber);

        Doc doc = daoDoc.loadById(user.docCode);
        docUser.setDocs(doc);

        us.addDocUser(docUser);

        Citizenship citizen = daoCitizenship.loadByCode(user.citizenshipCode);
        us.setCitizenship(citizen);

        this.dao.update(us);
    }

    @Override
    @Transactional
    public void delete(UserView user) {
        this.dao.delete(user.id);
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
            e.printStackTrace();
            return null;
        }
        return outDate;
    }
}
