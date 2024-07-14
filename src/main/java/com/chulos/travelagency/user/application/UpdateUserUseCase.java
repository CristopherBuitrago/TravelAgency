package com.chulos.travelagency.user.application;

import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.user.domain.service.UserService;

public class UpdateUserUseCase {
    // attribute
    private final UserService userService;

    // constructor
    public UpdateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    // method
    public String execute(User user) {
        return userService.updateUser(user);
    }
}
