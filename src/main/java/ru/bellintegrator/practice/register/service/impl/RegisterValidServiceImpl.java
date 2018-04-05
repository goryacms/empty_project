package ru.bellintegrator.practice.register.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.register.dao.RegisterDAO;
import ru.bellintegrator.practice.register.model.Register;
import ru.bellintegrator.practice.register.service.RegisterValidService;
import ru.bellintegrator.practice.register.view.RegisterView;
import ru.bellintegrator.practice.util.exceptionhandling.ResourceNotValidException;

@Service
public class RegisterValidServiceImpl implements RegisterValidService {
    private String message;

    private final RegisterDAO dao;

    public RegisterValidServiceImpl(RegisterDAO dao) {
        this.dao = dao;
    }


    @Override
    public void checkSave(RegisterView registerView) {
        message = "";
        if(registerView.login == null || registerView.password == null)
            message += "Логин и пароль обязательны для заполнения.";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }

    @Override
    public void checkUpdate(String activeCode) {
        message = "";
        if(activeCode == null )
            message += "Код не может быть пустым. ";

        Register reg = dao.loadByCode(activeCode);
        if(reg == null)
            message += "Не найден код активации";

        if(message.length() > 0) throw new ResourceNotValidException(message);
    }
}
