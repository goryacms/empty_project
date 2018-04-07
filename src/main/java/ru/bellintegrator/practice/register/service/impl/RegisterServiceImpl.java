package ru.bellintegrator.practice.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.register.dao.RegisterDAO;
import ru.bellintegrator.practice.register.model.Register;
import ru.bellintegrator.practice.register.service.RegisterService;
import ru.bellintegrator.practice.register.service.RegisterValidService;
import ru.bellintegrator.practice.register.view.RegisterView;

import ru.bellintegrator.practice.register.service.impl.HashService;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceInternalException;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotFoundException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    
    private HashService hashService;

    private final RegisterDAO dao;

    private RegisterValidService regValidService;

    @Autowired
    public RegisterServiceImpl(RegisterDAO dao, HashService hashService, RegisterValidService regValidService) {
        this.dao = dao;
        this.hashService = hashService;
        this.regValidService = regValidService;
    }



    @Override
    @Transactional
    public RegisterView loadByParams(RegisterView regView) {
        Register reg = new Register();
        reg.setLogin(regView.login);

        String password = formHashPassword(regView.password);

        reg.setPassword(password);

        reg.setActive(true);

        Register all = dao.loadByParams(reg);

        if(all == null)
            throw new ResourceNotFoundException("Данные пользователя не найдены");

        RegisterView view = new RegisterView();
        view.result = "success";
        return view;
    }

    @Override
    @Transactional
    public RegisterView save(RegisterView registerView) {
        regValidService.checkSave(registerView);

        Register reg = new Register();
        reg.setLogin(registerView.login);

        String password = formHashPassword(registerView.password);

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

        // Будет возвращать пустой JSON {} по причина system.out.println
        System.out.println(code);

        RegisterView view = new RegisterView();
        view.result = "success";
        return view;
    }

    @Override
    @Transactional
    public void update(String code) {
        regValidService.checkUpdate(code);

        Register reg = new Register();
        reg.setCode(code);
        reg.setActive(true);

        this.dao.update(reg);
    }

    private String formHashPassword(String psw){
        try {
            return hashService.generatePasswordHash(psw);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ResourceInternalException("При сохранении пароля возникла ошибка");
        }
    }

}