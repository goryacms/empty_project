package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    List<Organization> all();

    List<OrganizationView> loadByParams(OrganizationView organization);

    OrganizationView loadById(Long id);

    OrganizationView save(OrganizationView organization);

    OrganizationView update(OrganizationView organization);

    OrganizationView delete(OrganizationView organization);
}