package ru.bellintegrator.practice.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<User> all() {
        return this.dao.all();
    }

    @Override
    @Transactional
    public User loadById(Long id) {
        return this.dao.loadById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        this.dao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        this.dao.update(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.dao.delete(id);
    }
}
