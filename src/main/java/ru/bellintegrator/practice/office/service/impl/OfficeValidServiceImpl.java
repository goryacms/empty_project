package ru.bellintegrator.practice.office.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.service.OfficeValidService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;

@Service
public class OfficeValidServiceImpl implements OfficeValidService {
    final static String PHONE_PATT =  "^((8|\\+7)[\\-]?)?(\\(?\\d{2,6}\\)?[\\-]?)?[\\d\\-]{6,10}$";

    private String message;

    @Override
    public void checkSave(OfficeView officeView) {
        message = "";

        if(officeView.name == null || officeView.orgId == null || officeView.address == null)
            message += "Не все обязательные поля заполнены. \n";

        if(officeView.phone != null & !checkPhone(officeView.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkUpdate(OfficeView officeView) {
        message = "";

        if(officeView.id == null)
            message += "Не все обязательные поля заполнены. \n";

        if(officeView.phone != null & !checkPhone(officeView.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkList(OfficeView officeView) {
        message = "";


        if(officeView.orgId == null)
            message += "Не все обязательные поля заполнены. \n";

        if(officeView.phone != null  & !checkPhone(officeView.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    private boolean checkPhone(String val){
        try {
            return val.matches(PHONE_PATT);
        }catch (Exception e){
            return false;
        }
    }

}
