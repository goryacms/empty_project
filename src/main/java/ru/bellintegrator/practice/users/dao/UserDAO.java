package ru.bellintegrator.practice.users.dao;

import ru.bellintegrator.practice.users.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDAO {

    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> all();

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    /**
     * Получить объекты User по указанным параметрам
     * @return
     */
    List<User> loadByParams(User user);

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);

    /**
     * Обновить User
     *
     * @param user
     */
    void update(User user);

    /**
     * Удалить User по id
     *
     * @param id
     */
    void delete(Long id);
}
