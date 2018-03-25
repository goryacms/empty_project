package ru.bellintegrator.practice.office.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;

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
public class OfficeDAOImpl implements OfficeDAO {

    private static final Logger logger = LoggerFactory.getLogger(OfficeDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Office> loadByParams(Office office) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> q = cb.createQuery(Office.class);
        Root<Office> c = q.from(Office.class);

        Predicate p = cb.conjunction();

        p = cb.equal((c.get("organization").get("id")), office.getOrganization().getId());

        if(office.getName() != null)
            p = cb.and(p, cb.like((c.get("name")), "%"+office.getName()+"%"));

        if(office.getPhone() != null)
            p = cb.and(p, cb.equal((c.get("phone")), office.getPhone()));

        p = cb.and(p, cb.equal((c.get("isActive")), office.getActive()));

        q.select(c).where(p);

        TypedQuery<Office> query = em.createQuery(q);

        List<Office> officeList = query.getResultList();

        for(Office office2: officeList){
            logger.info("Organization list: " + office2);
        }

        return officeList;

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
        Office office1 = em.find(Office.class, office.getId());

        office1.setName(office.getName());
        office1.setAddress(office.getAddress());
        office1.setPhone(office.getPhone());
        office1.setActive(office.getActive());

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
