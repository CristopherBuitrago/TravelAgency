package com.chulos.travelagency.user.application;

import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.user.domain.service.UserService;

public class FindUserUseCase {
    // attribute
    private final UserService userService;

    // constructor
    public FindUserUseCase(UserService userService) {
        this.userService = userService;
    }

    // method to execute
    public User execute (int id) {
        return userService.findById(id);
    }
}
