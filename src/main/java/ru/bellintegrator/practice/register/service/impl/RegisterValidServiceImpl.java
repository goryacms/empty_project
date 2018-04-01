package ru.bellintegrator.practice.register.service.impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.register.service.RegisterValidService;
import ru.bellintegrator.practice.register.view.RegisterView;

@Service
public class RegisterValidServiceImpl implements RegisterValidService {
    @Override
    public boolean checkSave(RegisterView registerView) {
        if(registerView.login == null && registerView.password == null)
            return false;

        return true;
    }

    @Override
    public boolean checkUpdate(String activeCode) {
        if(activeCode == null )
            return false;

        return true;
    }
}
