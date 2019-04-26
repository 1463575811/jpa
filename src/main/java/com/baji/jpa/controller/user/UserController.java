package com.baji.jpa.controller.user;

import com.baji.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/user")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/userLogin/{username}/{password}")
    public ModelAndView userLogin(@PathVariable("username") String username, @PathVariable("password") String password) {


        return new ModelAndView("/front/index");
    }

}
