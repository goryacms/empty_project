package ru.bellintegrator.practice.register.service;

import ru.bellintegrator.practice.register.view.RegisterView;

public interface RegisterValidService {
    boolean checkSave(RegisterView registerView);

    boolean checkUpdate(String activeCode);

}
