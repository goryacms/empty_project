package ru.bellintegrator.practice.users.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.guides.dao.CitizenshipDAO;
import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.users.service.UserValidService;
import ru.bellintegrator.practice.users.view.UserView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserValidServiceImpl implements UserValidService {
    private final DocDAO docDAO;

    private CitizenshipDAO citizDAO;

    final static String DATE_FORMAT = "dd-MM-yyyy";

    final static String PHONE_PATT =  "^((8|\\+7)[\\-]?)?(\\(?\\d{2,6}\\)?[\\-]?)?[\\d\\-]{6,10}$";

    public UserValidServiceImpl(DocDAO docDAO, CitizenshipDAO citizDAO) {
        this.docDAO = docDAO;
        this.citizDAO = citizDAO;
    }

    @Override
    public boolean checkSave(UserView userView) {
        if(userView.officeId == null || userView.firstName == null ||
           userView.secondName == null || userView.docCode == null ||
           userView.docNumber == null || userView.docDate == null)
            return false;

        Doc doc = docDAO.loadById(userView.docCode);
        if(doc == null)
            return false;

        if(userView.citizenshipCode != null){
            Citizenship citizen = citizDAO.loadByCode(398L);
            if(citizen == null)
                return false;
        }

        if(userView.salary != null && userView.salary < 0)
            return false;

        if(userView.registrationDate != null &&  !isDateValid(userView.registrationDate) )
            return false;

        if(!isDateValid(userView.docDate))
            return false;

        if(userView.phone != null && !checkPhone(userView.phone))
            return false;



        return true;
    }

    @Override
    public boolean checkUpdate(UserView userView) {
        if(userView.id == null)
            return false;

        Doc doc = docDAO.loadById(userView.docCode);
        if(doc == null)
            return false;

        if(userView.citizenshipCode != null){
            Citizenship citizen = citizDAO.loadByCode(398L);
            if(citizen == null)
                return false;
        }

        if(userView.salary != null && userView.salary < 0)
            return false;

        if(userView.registrationDate != null &&  !isDateValid(userView.registrationDate) )
            return false;

        if(!isDateValid(userView.docDate))
            return false;

        if(userView.phone != null && !checkPhone(userView.phone))
            return false;



        return true;
    }

    @Override
    public boolean checkList(UserView userView) {
        if(userView.officeId == null)
            return false;

        return true;
    }


    private boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean checkPhone(String val){
        return val.matches(PHONE_PATT);
    }
}
