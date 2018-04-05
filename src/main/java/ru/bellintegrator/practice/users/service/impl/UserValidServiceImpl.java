package ru.bellintegrator.practice.users.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.guides.dao.CitizenshipDAO;
import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.users.service.UserValidService;
import ru.bellintegrator.practice.users.view.UserView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserValidServiceImpl implements UserValidService {
    private final DocDAO docDAO;

    private CitizenshipDAO citizDAO;

    private String message;

    final static String DATE_FORMAT = "dd.mm.yyyy";

    final static String PHONE_PATT =  "^((8|\\+7)[\\-]?)?(\\(?\\d{2,6}\\)?[\\-]?)?[\\d\\-]{6,10}$";



    public UserValidServiceImpl(DocDAO docDAO, CitizenshipDAO citizDAO) {
        this.docDAO = docDAO;
        this.citizDAO = citizDAO;
    }

    @Override
    public void checkSave(UserView userView) {
        message = "";
        if(userView.officeId == null || userView.firstName == null ||
           userView.secondName == null || userView.docCode == null ||
           userView.docNumber == null || userView.docDate == null)
            message += "Не все обязательные поля заполнены. \n";

        Doc doc = docDAO.loadById(userView.docCode);
        if(doc == null)
            message += "Не найден документ. \n";

        if(userView.citizenshipCode != null){
            Citizenship citizen = citizDAO.loadByCode(398L);
            if(citizen == null)
                message += "Не удалось найти гражданство. \n";
        }

        if(userView.salary != null & userView.salary < 0)
            message += "Цена не может быть меньше 0. \n";

        if(userView.registrationDate != null &  !isDateValid(userView.registrationDate) )
            message += "Проверьте дату регистрации. \n";

        if(!isDateValid(userView.docDate))
            message += "Проверьте дату документа. \n";

        if(userView.phone != null & !checkPhone(userView.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkUpdate(UserView userView) {
        message = "";
        if(userView.id == null)
            message += "Не все обязательные поля заполнены. \n";

        Doc doc = docDAO.loadById(userView.docCode);
        if(doc == null)
            message += "Не найден документ. \n";

        if(userView.citizenshipCode != null){
            Citizenship citizen = citizDAO.loadByCode(398L);
            if(citizen == null)
                message += "Не удалось найти гражданство. \n";
        }

        Double sal = userView.salary != null ? userView.salary : 0.00;
        if(sal < 0.00)
            message += "Цена не может быть меньше 0. \n";

        if(userView.registrationDate != null &  !isDateValid(userView.registrationDate) )
            message += "Проверьте дату регистрации. \n";

        if(!isDateValid(userView.docDate))
            message += "Проверьте дату документа. \n";

        if(userView.phone != null & !checkPhone(userView.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkList(UserView userView) {
        message += "";
        if(userView.officeId == null)
            message += "Не все обязательные поля заполнены. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
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
        try {
            return val.matches(PHONE_PATT);
        }catch (Exception e){
            return false;
        }
    }
}
