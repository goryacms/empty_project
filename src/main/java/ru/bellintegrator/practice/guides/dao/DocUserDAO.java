package ru.bellintegrator.practice.guides.dao;

import ru.bellintegrator.practice.guides.model.DocUser;

public interface DocUserDAO {
    /**
     * Получить DocUser по DocUserId
     */
    DocUser loadById(Integer id);

    Object loadByParams(Integer docId, Long userId);
}
