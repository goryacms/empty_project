package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;


/**
 * DAO для работы с Office
 */
public interface OfficeDAO {
    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);
}
