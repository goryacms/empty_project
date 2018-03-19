package ru.bellintegrator.practice.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.ResponseView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDAO dao;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao) {
        this.dao = dao;
    }


    @Override
    @Transactional
    public List<OfficeView> all() {
        List<Office> all = dao.all();

        Function<Office, OfficeView> mapOffice = p -> {
            OfficeView view = new OfficeView();
            view.id = p.getId();
            view.name = p.getName();
            view.isActive = p.getActive();

            return view;
        };

        return all.stream()
                .map(mapOffice)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OfficeView loadById(Long id) {
        Office p = this.dao.loadById(id);

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
    public ResponseView save(OfficeView officeView) {
        Office office = new Office();
        office.setName(officeView.name);
        office.setAddress(officeView.address);
        office.setPhone(officeView.phone);
        office.setActive(officeView.isActive);

        this.dao.save(office);

        return new ResponseView();
    }

    @Override
    @Transactional
    public ResponseView update(OfficeView officeView) {
        Office office = this.dao.loadById(officeView.id);

        office.setName(officeView.name);
        office.setAddress(officeView.address);
        office.setPhone(officeView.phone);
        office.setActive(officeView.isActive);

        this.dao.update(office);

        return new ResponseView();
    }

    @Override
    @Transactional
    public ResponseView delete(Long id) {
        this.dao.delete(id);

        return new ResponseView();
    }
}
