package ru.bellintegrator.practice.organization.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.service.OrganizationValidService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;

@Service
public class OrganizationValidServiceImpl implements OrganizationValidService {
    // Проверка телефона
    final static String PHONE_PATT =  "^((8|\\+7)[\\-]?)?(\\(?\\d{2,6}\\)?[\\-]?)?[\\d\\-]{6,10}$";

    private String message;

    @Override
    public void checkSave(OrganizationView organization) {
        message = "";

        if(organization.fullName == null || organization.inn == null ||
           organization.kpp == null || organization.address == null)
            message += "Не все обязательные поля заполнены. \n";

        if(organization.phone != null & !checkPhone(organization.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkUpdate(OrganizationView organization) {
        message = "";

        if(organization.id == null)
            message += "Не все обязательные поля заполнены. \n";

        if(organization.phone != null & !checkPhone(organization.phone))
            message += "Телефон введён неверно. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkList(OrganizationView organization) {
        message = "";

        if(organization.name == null)
            message += "Не все обязательные поля заполнены. \n";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    /**
     * Доступные номера: 89271111111, 8-927-111-11-11, (8412)11-11-11, 8412-11-11-11, 111111, 11-11-11
     */
    // Может вынести в отдельный класс? Например, в пакет util ?
    private boolean checkPhone(String val){
        try {
            return val.matches(PHONE_PATT);
        }catch (Exception e){
            return false;
        }
    }

}
