package ru.bellintegrator.practice.users.service;

import ru.bellintegrator.practice.users.view.UserView;

public interface UserValidService {
    void checkSave(UserView userView);

    void checkUpdate(UserView userView);

    void checkList(UserView userView);
}
