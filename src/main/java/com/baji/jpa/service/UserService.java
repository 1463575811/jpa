package com.baji.jpa.service;

import com.baji.jpa.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User login(String username, String password);

}
