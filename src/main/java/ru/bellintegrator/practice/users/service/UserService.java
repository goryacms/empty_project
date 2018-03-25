package ru.bellintegrator.practice.users.service;

import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.List;

public interface UserService {
    List<User> all();

    List<UserView> loadByParams(UserView user);

    UserView loadById(Long id);

    void save(UserView user);

    void update(UserView user);

    void delete(UserView user);
}
