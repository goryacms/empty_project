package ru.bellintegrator.practice.register.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.register.dao.RegisterDAO;
import ru.bellintegrator.practice.register.model.Register;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RegisterDAOImpl implements RegisterDAO {

    private static final Logger logger = LoggerFactory.getLogger(RegisterDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public RegisterDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Register loadByParams(Register register) {
        Register resp = null;

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Register> criteria = builder.createQuery(Register.class);

        Root<Register> reg = criteria.from(Register.class);

        Predicate where = builder.conjunction();
        where = builder.equal(reg.get("login"), register.getLogin());
        where = builder.and(where, builder.equal(reg.get("password"), register.getPassword()));
        where = builder.and(where, builder.equal(reg.get("isActive"), register.getActive()));
        criteria.where(where);

        TypedQuery<Register> query = em.createQuery(criteria);

        try {
            resp = query.getSingleResult();
        }catch(NoResultException e){
            //e.printStackTrace();
            return null;
        }
        return resp;
    }

    @Override
    public void save(Register register) {
        em.persist(register);
        logger.info("Register successfully saved. Details: " + register);
    }


    @Override
    public Register loadByCode(String code) {
        Register resp = null;

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Register> criteria = builder.createQuery(Register.class);

        Root<Register> reg = criteria.from(Register.class);

        criteria.where(builder.equal(reg.get("code"), code));

        TypedQuery<Register> query = em.createQuery(criteria);

        try {
            resp = query.getSingleResult();
        }catch(NoResultException e){
            //e.printStackTrace();
            return null;
        }
        return resp;
    }

   
    @Override
    public void update(Register register) {
        Register reg = this.loadByCode(register.getCode());

        reg.setActive(register.getActive());

        logger.info("Register successfully updated. Details: " + register);
    }


}
