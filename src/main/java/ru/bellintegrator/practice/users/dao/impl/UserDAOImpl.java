package ru.bellintegrator.practice.users.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;

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
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> loadByParams(User user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> c = q.from(User.class);

        Predicate p = cb.conjunction();

        p = cb.equal((c.get("office").get("id")), user.getOffice().getId());

        if(user.getFirstName() != null)
            p = cb.and(cb.like((c.get("firstName")), "%"+user.getFirstName()+"%"));

        if(user.getLastName() != null)
            p = cb.and(cb.like((c.get("lastName")), "%"+user.getLastName()+"%"));

        if(user.getMiddleName() != null)
            p = cb.and(cb.like((c.get("middleName")), "%"+user.getMiddleName()+"%"));

        if(user.getPosition() != null)
            p = cb.and(cb.like((c.get("position")), "%"+user.getPosition()+"%"));

        if(user.getCitizenship() != null)
            p = cb.and(cb.equal((c.get("citizenship").get("code")), user.getCitizenship().getCode()));

        q.select(c).where(p);

        TypedQuery<User> query = em.createQuery(q);

        List<User> userList = query.getResultList();

        for(User user1: userList){
            logger.info("User list: " + user1);
        }

        return userList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> all() {
        List<User> userList = em.createQuery("from User").getResultList();
        for(User user: userList){
            logger.info("User list: " + user);
        }
        return userList;
    }

    @Override
    public User loadById(Long id) {
        User user = em.find(User.class, id);
        logger.info("User successfully load by id ("+id+"). Details: " + user);
        return user;
    }

    @Override
    public long save(User user) {
        em.persist(user);
        em.flush();
        logger.info("User successfully saved. Details: " + user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        User user1 = em.find(User.class, user.getId());

        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setMiddleName(user.getMiddleName());
        user1.setPosition(user.getPosition());
        user1.setSalary(user.getSalary());
        user1.setRegistrationDate(user.getRegistrationDate());
        user1.setPhone(user.getPhone());

        user1.setDocUsers(user.getDocUsers());

        user1.setCitizenship(user.getCitizenship());

        logger.info("User successfully updated. Details: " + user);
    }

    @Override
    public void delete(Long id) {
        User user = (User) em.find(User.class, id);

        if(user != null){
            em.remove(user);
        }
        logger.info("User successfully deleted. Details: " + user);
    }
}
