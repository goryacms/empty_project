package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.view.OfficeView;

public interface OfficeValidService {

    boolean checkSave(OfficeView officeView);

    boolean checkUpdate(OfficeView officeView);

    boolean checkList(OfficeView officeView);

}
