package ru.bellintegrator.practice.guides.dao.impl;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.model.Doc;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
@Repository
public class DocDAOImpl implements DocDAO {
    private final EntityManager em;

    public DocDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Doc loadById(Integer id) {
        Doc doc = em.find(Doc.class, id);
        return doc;
    }
}
