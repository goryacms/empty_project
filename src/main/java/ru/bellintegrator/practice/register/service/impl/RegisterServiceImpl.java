package ru.bellintegrator.practice.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.register.dao.RegisterDAO;
import ru.bellintegrator.practice.register.model.Register;
import ru.bellintegrator.practice.register.service.RegisterService;
import ru.bellintegrator.practice.register.view.RegisterView;

import ru.bellintegrator.practice.register.service.impl.HashService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    
    @Autowired
    HashService hashService;

    public RegisterServiceImpl(RegisterDAO dao, HashService hashService) {
        this.dao = dao;
        this.hashService = hashService;
    }

    private final RegisterDAO dao;

    @Override
    @Transactional
    public RegisterView loadByParams(RegisterView regView) {
        Register reg = new Register();
        reg.setLogin(regView.login);

        String password = null;
        try {
            password = hashService.generatePasswordHash(regView.password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        reg.setPassword(password);

        reg.setActive(true);

        Register all = dao.loadByParams(reg);

        RegisterView view = new RegisterView();
        view.login = all.getLogin();
        view.password = all.getPassword();

        return view;
    }

    @Override
    @Transactional
    public void save(RegisterView registerView) {
        Register reg = new Register();
        reg.setLogin(registerView.login);

        String password = null;
        try {
            password = hashService.generatePasswordHash(registerView.password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        reg.setPassword(password);
        reg.setEmail(registerView.email);
        reg.setName(registerView.name);

        reg.setActive(false);
        
        String code = hashService.generateShortCode();
        reg.setCode(code);

        Register all = this.dao.loadByParams(reg);

        if(null == all) {
            this.dao.save(reg);
        }

        System.out.println(code);
    }


    @Override
    @Transactional
    public void update(String code) {
        Register reg = new Register();
        reg.setCode(code);
        reg.setActive(true);

        this.dao.update(reg);

    }

}