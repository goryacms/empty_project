package ru.bellintegrator.practice.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public OrganizationView loadById(Long id) {
        Organization p = this.dao.loadById(id);

        //Function<Organization, OrganizationView> mapOrg = p -> {
            OrganizationView view = new OrganizationView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.fullName = p.getFullName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.address = p.getAddress();
            view.phone = p.getPhone();
            view.isActive = p.getActive();

            return view;
        //};

        //return mapOrg;
        //return this.dao.loadById(id);
    }

    @Override
    @Transactional
    public void save(OrganizationView orgView) {
        Organization org = new Organization();
        org.setName(orgView.name);
        org.setFullName(orgView.fullName);
        org.setInn(orgView.inn);
        org.setKpp(orgView.kpp);
        org.setAddress(orgView.address);
        org.setPhone(orgView.phone);
        org.setActive(orgView.isActive);

        this.dao.save(org);
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