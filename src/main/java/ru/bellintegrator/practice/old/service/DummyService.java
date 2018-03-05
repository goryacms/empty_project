package ru.bellintegrator.practice.old.service;

import ru.bellintegrator.practice.old.view.PersonView;

import java.util.List;

/**
 * Some service. Just for test
 */
public interface DummyService {

    /**
     *
     * @param person
     */
    void add(PersonView person);

    /**
     * Dummy service method
     * @return {@Person}
     */
    List<PersonView> persons();
}