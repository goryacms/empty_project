package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

public interface OfficeService {
    List<Office> all();

    List<OfficeView> loadByParams(OfficeView office);

    OfficeView loadById(Long id);

    OfficeView save(OfficeView office);

    OfficeView update(OfficeView office);

    OfficeView delete(OfficeView office);
}
