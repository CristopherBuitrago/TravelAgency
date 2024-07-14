package com.chulos.travelagency.user.application;

import com.chulos.travelagency.user.domain.service.UserService;

public class DeleteUserUseCase {
    // attributes
    private final UserService userService;

    // constructor to get the service
    public DeleteUserUseCase(UserService userService) {
        this.userService = userService;
    }

    // execute delete
    public String execute(int id) {
        return userService.deleteUser(id);
    }
}
