package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    List<Organization> all();

    List<OrganizationView> loadByParams(OrganizationView organization);

    OrganizationView loadById(Long id);

    void save(OrganizationView organization);

    void update(OrganizationView organization);

    void delete(OrganizationView organization);
}