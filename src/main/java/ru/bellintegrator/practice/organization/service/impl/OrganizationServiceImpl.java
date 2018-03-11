package ru.bellintegrator.practice.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDAO dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Organization> all() {
        return this.dao.all();
    }

    @Override
    @Transactional
    public Organization loadById(Long id) {
        return this.dao.loadById(id);
    }

    @Override
    @Transactional
    public void save(Organization organization) {
        this.dao.save(organization);
    }

    @Override
    @Transactional
    public void update(Organization organization) {
        this.dao.update(organization);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.dao.delete(id);
    }
}