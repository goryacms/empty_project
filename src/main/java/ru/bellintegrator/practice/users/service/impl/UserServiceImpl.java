package ru.bellintegrator.practice.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.users.dao.UserDAO;
import ru.bellintegrator.practice.users.model.User;
import ru.bellintegrator.practice.users.service.UserService;
import ru.bellintegrator.practice.users.view.UserView;

import java.util.List;
import java.util.function.Function;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {
    private final UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<User> all() {
        List<User> all = dao.all();

        Function<User, UserView> mapUser = p -> {
            UserView view = new UserView();
            view.id         = p.getId();
            view.firstName  = p.getFirstName();
            view.secondName = p.getLastName();
            view.middleName = p.getMiddleName();
            view.position   = p.getPosition();

            return view;
        };
        /*
        return all.stream()
                .map(mapUser)
                .collect(Collectors.toList());
                */
        return null;
    }

    @Override
    @Transactional
    public User loadById(Long id) {
        //return this.dao.loadById(id);
        return null;
    }

    @Override
    @Transactional
    public void save(UserView user) {
        //this.dao.save(user);
        System.out.println("555");
    }

    @Override
    @Transactional
    public void update(UserView user) {
        //this.dao.update(user);
        System.out.println("555");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.dao.delete(id);
    }
}
