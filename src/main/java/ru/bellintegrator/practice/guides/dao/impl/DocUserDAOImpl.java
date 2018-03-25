package ru.bellintegrator.practice.guides.dao.impl;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.guides.dao.DocUserDAO;
import ru.bellintegrator.practice.guides.model.DocUser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Set;

@Repository
public class DocUserDAOImpl implements DocUserDAO {
    private final EntityManager em;

    public DocUserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public DocUser loadById(Integer id) {
        DocUser docUser = em.find(DocUser.class, id);
        return docUser;
    }

    @Override
    public DocUser loadByParams(Integer docId, Long userId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocUser> criteria = builder.createQuery(DocUser.class);

        Root<DocUser> docUser = criteria.from(DocUser.class);

        Predicate where = builder.conjunction();
        where = builder.equal(docUser.get("doc").get("id"), docId);
        where = builder.and(where, builder.equal(docUser.get("user").get("id"), userId));
        criteria.where(where);

        TypedQuery<DocUser> query = em.createQuery(criteria);

        return query.getSingleResult();
    }

}
