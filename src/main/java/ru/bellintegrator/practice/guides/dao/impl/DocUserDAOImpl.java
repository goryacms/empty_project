package ru.bellintegrator.practice.guides.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.guides.dao.DocUserDAO;
import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.guides.view.DocUserView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class DocUserDAOImpl implements DocUserDAO {
    private static final Logger logger = LoggerFactory.getLogger(DocUserDAOImpl.class);

    private final EntityManager em;

    public DocUserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public DocUser loadById(Long id) {
        DocUser docUser = em.find(DocUser.class, id);
        return docUser;
    }


    @Override
    public DocUser loadByParams(DocUserView docUserView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocUser> criteria = builder.createQuery(DocUser.class);

        Root<DocUser> docUser = criteria.from(DocUser.class);

        Predicate where = builder.conjunction();
        where = builder.equal(docUser.get("doc").get("id"), docUserView.docId);
        where = builder.and(where, builder.equal(docUser.get("user").get("id"), docUserView.userId));
        criteria.where(where);

        TypedQuery<DocUser> query = em.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public Long save(DocUser docUser) {
        em.persist(docUser);
        em.flush();
        logger.info("DocUser successfully saved. Details: " + docUser);

        return docUser.getId();
    }
}
