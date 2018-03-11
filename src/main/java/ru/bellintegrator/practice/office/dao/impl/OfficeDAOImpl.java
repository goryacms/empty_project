package ru.bellintegrator.practice.office.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private static final Logger logger = LoggerFactory.getLogger(OfficeDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Office> all() {
        List<Office> officeList = em.createQuery("from Office").getResultList();
        for(Office office: officeList){
            logger.info("Office list: " + office);
        }
        return officeList;
    }

    @Override
    public Office loadById(Long id) {
        Office office = em.find(Office.class, id);
        logger.info("Office successfully load by id ("+id+"). Details: " + office);
        return office;
    }

    @Override
    public void save(Office office) {
        em.persist(office);
        logger.info("Office successfully saved. Details: " + office);
    }

    @Override
    public void update(Office office) {
        em.merge(office);
        logger.info("Office successfully updated. Details: " + office);
    }

    @Override
    public void delete(Long id) {
        Office office = (Office) em.find(Office.class, id);

        if(office != null){
            em.remove(office);
        }
        logger.info("Office successfully deleted. Details: " + office);
    }
}
