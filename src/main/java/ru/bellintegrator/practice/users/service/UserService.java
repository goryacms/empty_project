package ru.bellintegrator.practice.users.service;

import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.List;

public interface UserService {
    List<User> all();

    User loadById(Long id);

    void save(UserView user);

    void update(UserView user);

    void delete(Long id);
}
