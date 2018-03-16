package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    List<Organization> all();

    OrganizationView loadById(Long id);

    void save(OrganizationView organization);

    void update(Organization organization);

    void delete(Long id);
}
