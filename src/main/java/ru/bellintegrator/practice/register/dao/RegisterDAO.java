package ru.bellintegrator.practice.register.dao;

import ru.bellintegrator.practice.register.model.Register;

import java.util.List;

/**
 * DAO для работы с Register
 */
public interface RegisterDAO {
    /**
     * Получить объекты Organization по указанным параметрам
     * @return
     */
    Register loadByParams(Register register);


    /**
     * Сохранить Register
     *
     * @param register
     */
    void save(Register register);

    Register loadByCode(String code);

    /**
     * Обновить Register
     */
    void update(Register register);
}

