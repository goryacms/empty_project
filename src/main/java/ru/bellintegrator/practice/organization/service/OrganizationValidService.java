package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationValidService {

    boolean checkSave(OrganizationView organization);

    boolean checkUpdate(OrganizationView organization);

    boolean checkList(OrganizationView organization);

}
