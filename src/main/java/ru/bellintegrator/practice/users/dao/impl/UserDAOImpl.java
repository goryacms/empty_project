package ru.bellintegrator.practice.users.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;

import javax.persistence.EntityManager;
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
    public void save(User user) {
        em.persist(user);
        logger.info("User successfully saved. Details: " + user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
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
