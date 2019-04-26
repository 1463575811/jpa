package com.baji.jpa.service.impl;

import com.baji.jpa.entity.User;
import com.baji.jpa.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User login(String username, String password) {
        var str1 = "123";
        var user = User.builder().build();
        return user;
    }
}
