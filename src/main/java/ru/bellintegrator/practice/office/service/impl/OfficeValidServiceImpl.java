package ru.bellintegrator.practice.office.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.service.OfficeValidService;
import ru.bellintegrator.practice.office.view.OfficeView;

@Service
public class OfficeValidServiceImpl implements OfficeValidService {

    final static String PHONE_PATT =  "^((8|\\+7)[\\-]?)?(\\(?\\d{2,6}\\)?[\\-]?)?[\\d\\-]{6,10}$";

    @Override
    public boolean checkSave(OfficeView officeView) {
        if(officeView.name == null || officeView.orgId == null || officeView.address == null)
            return false;

        if(officeView.phone != null && !checkPhone(officeView.phone))
            return false;

        return true;
    }

    @Override
    public boolean checkUpdate(OfficeView officeView) {
        if(officeView.id == null)
            return false;

        if(officeView.phone != null && !checkPhone(officeView.phone))
            return false;

        return true;
    }

    @Override
    public boolean checkList(OfficeView officeView) {
        if(officeView.id == null)
            return false;

        if(officeView.phone != null && !checkPhone(officeView.phone))
            return false;

        return true;
    }


    private boolean checkPhone(String val){
        return val.matches(PHONE_PATT);
    }
}
