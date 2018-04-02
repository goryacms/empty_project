package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.view.OfficeView;

public interface OfficeValidService {

    void checkSave(OfficeView officeView);

    void checkUpdate(OfficeView officeView);

    void checkList(OfficeView officeView);

}
