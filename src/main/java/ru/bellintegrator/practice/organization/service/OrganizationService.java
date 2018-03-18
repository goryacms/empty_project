package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.ResponseView;

import java.util.List;

public interface OrganizationService {
    List<OrganizationView> all();

    OrganizationView loadById(Long id);

    ResponseView save(OrganizationView organization);

    ResponseView update(OrganizationView organization);

    ResponseView delete(Long id);
}