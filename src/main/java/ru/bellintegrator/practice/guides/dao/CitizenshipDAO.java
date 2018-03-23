package ru.bellintegrator.practice.guides.dao;

import ru.bellintegrator.practice.guides.model.Citizenship;

public interface CitizenshipDAO {
    /**
     * Получить Citizenship по идентификатору
     *
     * @param id
     */
    Citizenship loadById(Long id);

    /**
     * Получить Citizenship по коду страны
     *
     * @param code
     */
    Citizenship loadByCode(Long code);
}
