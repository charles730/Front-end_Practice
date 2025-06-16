package com.neusoft.elm.service;

import com.neusoft.elm.po.User;

public interface UserService {
    User getUserByIdByPass(String userId, String password);

    int getUserById(String userId);

    int saveUser(User user);
}