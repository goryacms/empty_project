package ru.bellintegrator.practice.register.service;


import ru.bellintegrator.practice.register.model.Register;
import ru.bellintegrator.practice.register.view.RegisterView;

import java.util.List;

public interface RegisterService {
    RegisterView loadByParams(RegisterView registerView);

    void save(RegisterView registerView);

    void update(String code);
}