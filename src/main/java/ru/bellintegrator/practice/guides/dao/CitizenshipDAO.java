package ru.bellintegrator.practice.guides.dao;

import ru.bellintegrator.practice.guides.model.Citizenship;

import java.util.List;

public interface CitizenshipDAO {
    /**
     * Получить Citizenship по коду страны
     *
     * @param code
     */
    Citizenship loadByCode(Long code);

    /**
     * Получить все объекты Docs
     */
    List<Citizenship> all();
}
