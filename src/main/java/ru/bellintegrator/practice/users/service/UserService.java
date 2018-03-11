package ru.bellintegrator.practice.users.service;

import ru.bellintegrator.practice.users.model.User;

import java.util.List;

public interface UserService {
    List<User> all();

    User loadById(Long id);

    void save(User user);

    void update(User user);

    void delete(Long id);
}
