package ru.bellintegrator.practice.guides.dao.impl;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.guides.dao.CitizenshipDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;

import javax.persistence.EntityManager;


/**
 * {@inheritDoc}
 */
@Repository
public class CitizenshipDAOImpl implements CitizenshipDAO {
    private final EntityManager em;

    public CitizenshipDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Citizenship loadById(Long id) {
        Citizenship citiz = em.find(Citizenship.class, id);
        return citiz;
    }

    @Override
    public Citizenship loadByCode(Long code) {
        Citizenship citiz = em.find(Citizenship.class, code);
        return citiz;
    }
}
