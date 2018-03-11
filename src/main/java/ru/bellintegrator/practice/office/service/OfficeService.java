package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

public interface OfficeService {
    List<Office> all();

    Office loadById(Long id);

    void save(Office office);

    void update(Office office);

    void delete(Long id);
}
