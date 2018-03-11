package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDAO {
    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> all();

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * Обновить Organization
     *
     * @param organization
     */
    void update(Organization organization);

    /**
     * Удалить Organization по id
     *
     * @param id
     */
    void delete(Long id);
}

