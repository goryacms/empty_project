package ru.bellintegrator.practice.users.service;

import ru.bellintegrator.practice.users.view.UserView;

public interface UserValidService {
    boolean checkSave(UserView userView);

    boolean checkUpdate(UserView userView);

    boolean checkList(UserView userView);
}
