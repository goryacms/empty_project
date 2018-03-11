package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

public interface OrganizationService {
    List<Organization> all();

    Organization loadById(Long id);

    void save(Organization organization);

    void update(Organization organization);

    void delete(Long id);
}
