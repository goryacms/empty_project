package ru.bellintegrator.practice.organization.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.service.OrganizationValidService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

@Service
public class OrganizationValidServiceImpl implements OrganizationValidService {
    // Проверка телефона
    final static String PHONE_PATT =  "^((8|\\+7)[\\-]?)?(\\(?\\d{2,6}\\)?[\\-]?)?[\\d\\-]{6,10}$";

    @Override
    public boolean checkSave(OrganizationView organization) {
        if(organization.fullName == null || organization.inn == null ||
           organization.kpp == null || organization.address == null)
            return false;

        if(organization.phone != null && !checkPhone(organization.phone))
            return false;

        return true;
    }

    @Override
    public boolean checkUpdate(OrganizationView organization) {
        if(organization.id == null)
            return false;

        if(organization.phone != null && !checkPhone(organization.phone))
            return false;

        return true;
    }

    @Override
    public boolean checkList(OrganizationView organization) {
        if(organization.name == null)
            return false;

        return true;
    }

    /**
     * Доступные номера: 89271111111, 8-927-111-11-11, (8412)11-11-11, 8412-11-11-11, 111111, 11-11-11
     */
    // Может вынести в отдельный класс? Например, в пакет util ?
    private boolean checkPhone(String val){
        return val.matches(PHONE_PATT);
    }

}
