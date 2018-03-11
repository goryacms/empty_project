package ru.bellintegrator.practice.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.service.OfficeService;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDAO dao;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao) {
        this.dao = dao;
    }


    @Override
    @Transactional
    public List<Office> all() {
        return this.dao.all();
    }

    @Override
    @Transactional
    public Office loadById(Long id) {
        return this.dao.loadById(id);
    }

    @Override
    @Transactional
    public void save(Office office) {
        this.dao.save(office);
    }

    @Override
    @Transactional
    public void update(Office office) {
        this.dao.update(office);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.dao.delete(id);
    }
}
