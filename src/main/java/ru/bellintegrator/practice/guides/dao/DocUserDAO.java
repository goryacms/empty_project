package ru.bellintegrator.practice.guides.dao;

import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.guides.view.DocUserView;

public interface DocUserDAO {
    /**
     * Получить DocUser по DocUserId
     */
    DocUser loadById(Long id);

    Object loadByParams(DocUserView docUserView);

    Long save(DocUser user);
}
