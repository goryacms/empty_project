package ru.bellintegrator.practice.organization.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Organization> all() {
        List<Organization> orgList = em.createQuery("from Organization").getResultList();
        for(Organization organization: orgList){
            logger.info("Organization list: " + organization);
        }
        return orgList;
    }

    @Override
    public Organization loadById(Long id) {
        Organization organization = em.find(Organization.class, id);
        logger.info("Organization successfully load by id ("+id+"). Details: " + organization);
        return organization;
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
        logger.info("Organization successfully saved. Details: " + organization);
    }

    @Override
    public void update(Organization organization) {
        em.merge(organization);
        logger.info("Organization successfully updated. Details: " + organization);
    }

    @Override
    public void delete(Long id) {
        Organization organization = (Organization) em.find(Organization.class, id);

        if(organization != null){
            em.remove(organization);
        }
        logger.info("Organization successfully deleted. Details: " + organization);
    }
}
