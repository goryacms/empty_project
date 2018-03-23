package ru.bellintegrator.practice.guides.dao;

import ru.bellintegrator.practice.guides.model.Doc;

/**
 * Создал DAO для справочника документов, т.к. необходимо будет
 * загружать конкретный документ по коду документа (code в бд, id в модели)
 */
public interface DocDAO {

    /**
     * Получить User по идентификатору
     *
     * @param id
     */
    Doc loadById(Integer id);
}
