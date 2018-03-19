package ru.bellintegrator.practice.office.service;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.ResponseView;

import java.util.List;

public interface OfficeService {
    List<OfficeView> all();

    OfficeView loadById(Long id);

    ResponseView save(OfficeView office);

    ResponseView update(OfficeView office);

    ResponseView delete(Long id);
}
