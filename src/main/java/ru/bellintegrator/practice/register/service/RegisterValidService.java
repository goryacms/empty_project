package ru.bellintegrator.practice.register.service;

import ru.bellintegrator.practice.register.view.RegisterView;

public interface RegisterValidService {
    void checkSave(RegisterView registerView);

    void checkUpdate(String activeCode);

}
