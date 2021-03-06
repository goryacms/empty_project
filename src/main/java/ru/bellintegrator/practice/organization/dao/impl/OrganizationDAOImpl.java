package ru.bellintegrator.practice.organization.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    public List<Organization> loadByParams(Organization organization) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> q = cb.createQuery(Organization.class);
        Root<Organization> c = q.from(Organization.class);

        Predicate p = cb.conjunction();

        p = cb.like((c.get("name")), "%"+organization.getName()+"%");

        if(organization.getActive() != null )
            p = cb.and(p, cb.equal((c.get("isActive")), organization.getActive()));

        if(organization.getInn() != null)
            p = cb.and(p, cb.equal((c.get("inn")), organization.getInn()));

        q.select(c).where(p);

        TypedQuery<Organization> query = em.createQuery(q);

        List<Organization> orgList = query.getResultList();

        for(Organization organization2: orgList){
            logger.info("Organization list: " + organization2);
        }


        return orgList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT org FROM Organization org", Organization.class);
        return query.getResultList();
    }

    @Override
    public Organization loadById(Long id)  {
        Organization organization = em.find(Organization.class, id);
        logger.info("Organization successfully load by id ("+id+"). Details: " + organization);
        return organization;
    }

    @Override
    public long save(Organization organization) {
        em.persist(organization);
        em.flush();
        logger.info("Organization successfully saved. Details: " + organization);
        return organization.getId();
    }

    @Override
    public void update(Organization organization) {
        Organization org = em.find(Organization.class, organization.getId());

        org.setName(organization.getName());
        org.setName(organization.getName());
        org.setFullName(organization.getFullName());
        org.setInn(organization.getInn());
        org.setKpp(organization.getKpp());
        org.setAddress(organization.getAddress());
        org.setPhone(organization.getPhone());
        org.setActive(organization.getActive());

        //em.merge(org);
        logger.info("Organization successfully updated. Details: " + organization);
    }

    @Override
    public void delete(Long id) {
        Organization organization = em.find(Organization.class, id);

        logger.info("Organization successfully deleted. Details: " + organization);

        if(organization != null){
            em.remove(organization);
        }

    }


}
