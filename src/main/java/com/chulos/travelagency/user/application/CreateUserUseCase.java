package com.chulos.travelagency.user.application;

import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.user.domain.service.UserService;

public class CreateUserUseCase {
    // attributes
    private final UserService userService;

    // constructor
    public CreateUserUseCase(UserService userService) {
        this.userService = userService;
    }
    
    // execute
    public String execute (User user) {
        return userService.createUser(user);
    }
}
