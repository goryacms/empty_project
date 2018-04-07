package ru.bellintegrator.practice.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.service.OrganizationValidService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDAO dao;
    private final OrganizationValidService orgValidService;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao, OrganizationValidService orgValidService) {
        this.dao = dao;
        this.orgValidService = orgValidService;
    }

    @Override
    @Transactional
    public List<OrganizationView> loadByParams(OrganizationView orgView) {
        orgValidService.checkList(orgView);

        Organization org = new Organization();
        org.setName(orgView.name);
        org.setInn(orgView.inn);
        org.setActive(orgView.isActive);

        List<Organization> all = dao.loadByParams(org);

        if(all.size() == 0) {
            throw new ResourceNotFoundException("Информация по заданным условиям не найдена");
        }

        Function<Organization, OrganizationView> mapOrg = p -> {
            OrganizationView view = new OrganizationView();
            view.id = p.getId();
            view.name = p.getName();
            view.isActive = p.getActive();

            view.officesCount = p.getOffices().size();

            return view;
        };

        return all.stream()
                .map(mapOrg)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<Organization> all() {
        List<Organization> all = dao.all();
        return all;
    }

    @Override
    @Transactional
    public OrganizationView loadById(Long id) {
        Organization p = this.dao.loadById(id);

        if(p == null)
            throw new ResourceNotFoundException("Организация с идентификатором = " + id + " не найдена");

        OrganizationView view = new OrganizationView();

        view.id = p.getId();
        view.name = p.getName();
        view.fullName = p.getFullName();
        view.inn = p.getInn();
        view.kpp = p.getKpp();
        view.address = p.getAddress();
        view.phone = p.getPhone();
        view.isActive = p.getActive();
        return view;
    }


    @Override
    @Transactional
    public OrganizationView save(OrganizationView orgView) {
        orgValidService.checkSave(orgView);

        Organization org = new Organization();
        org.setName(orgView.name);
        org.setFullName(orgView.fullName);
        org.setInn(orgView.inn);
        org.setKpp(orgView.kpp);
        org.setAddress(orgView.address);
        org.setPhone(orgView.phone);
        org.setActive(orgView.isActive);

        this.dao.save(org);

        OrganizationView view = new OrganizationView();
        view.id = org.getId();
        return view;
    }

    @Override
    @Transactional
    public OrganizationView update(OrganizationView orgView) {
        orgValidService.checkUpdate(orgView);

        Organization org = new Organization();

        org.setId(orgView.id);
        org.setName(orgView.name);
        org.setFullName(orgView.fullName);
        org.setInn(orgView.inn);
        org.setKpp(orgView.kpp);
        org.setAddress(orgView.address);
        org.setPhone(orgView.phone);
        org.setActive(orgView.isActive);

        this.dao.update(org);

        OrganizationView view = new OrganizationView();
        view.result = "success";
        return view;
    }

    @Override
    @Transactional
    public OrganizationView delete(OrganizationView organization) {
        this.dao.delete(organization.id);

        OrganizationView view = new OrganizationView();
        view.result = "success";
        return view;
    }
}