package ru.bellintegrator.practice.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.service.OfficeValidService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDAO dao;
    private final OrganizationDAO daoOrg;

    private final OfficeValidService officeValidService;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao, OrganizationDAO daoOrg, OfficeValidService officeValidService) {
        this.dao    = dao;
        this.daoOrg = daoOrg;
        this.officeValidService = officeValidService;
    }

    @Override
    @Transactional
    public List<OfficeView> loadByParams(OfficeView officeView) {
        officeValidService.checkList(officeView);

        Office office = new Office();

        office.setName(officeView.name);
        office.setPhone(officeView.phone);

        office.setActive(officeView.isActive);

        Organization org = daoOrg.loadById(officeView.orgId);
        office.setOrganization(org);



        List<Office> all = dao.loadByParams(office);

        if(all.size() == 0) {
            throw new ResourceNotFoundException("Информация по заданным условиям не найдена");
        }

        Function<Office, OfficeView> mapOffice = p -> {
            OfficeView view = new OfficeView();
            view.id = p.getId();
            view.name = p.getName();
            view.isActive = p.getActive();

            view.organizationName = p.getOrganization().getName();

            return view;
        };

        return all.stream()
                .map(mapOffice)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<Office> all() {
        List<Office> all = dao.all();
        return all;
    }

    @Override
    @Transactional
    public OfficeView loadById(Long id) {
        Office p = this.dao.loadById(id);

        if(p == null)
            throw new ResourceNotFoundException("Офис с идентификатором = " + id + " не найден");

        OfficeView view = new OfficeView();
        view.id = p.getId();
        view.name = p.getName();
        view.address = p.getAddress();
        view.phone = p.getPhone();
        view.isActive = p.getActive();

        return view;
    }

    @Override
    @Transactional
    public OfficeView save(OfficeView officeView) {
        officeValidService.checkSave(officeView);

        Office office = new Office();
        office.setName(officeView.name);
        office.setAddress(officeView.address);
        office.setPhone(officeView.phone);
        office.setActive(officeView.isActive);

        this.dao.save(office);

        OfficeView view = new OfficeView();
        view.id = office.getId();
        return view;
    }

    @Override
    @Transactional
    public OfficeView update(OfficeView officeView) {
        officeValidService.checkUpdate(officeView);

        Office office = new Office();

        office.setId(officeView.id);
        office.setName(officeView.name);
        office.setAddress(officeView.address);
        office.setPhone(officeView.phone);
        office.setActive(officeView.isActive);

        this.dao.update(office);

        OfficeView view = new OfficeView();
        view.result = "success";
        return view;
    }

    @Override
    @Transactional
    public OfficeView delete(OfficeView office) {
        this.dao.delete(office.id);


        /*Organization org =
        daoOrg.update();*/

        OfficeView view = new OfficeView();
        view.result = "success";
        return view;
    }
}
