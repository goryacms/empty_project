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
     * Получить объекты Office по указанным параметрам
     * @return
     */
    List<Office> loadByParams(Office office);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);

    /**
     * Обновить Office
     *
     * @param office
     */
    void update(Office office);

    /**
     * Удалить Office по id
     *
     * @param id
     */
    void delete(Long id);
}
