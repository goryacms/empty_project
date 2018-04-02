package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationValidService {

    void checkSave(OrganizationView organization);

    void checkUpdate(OrganizationView organization);

    void checkList(OrganizationView organization);

}
