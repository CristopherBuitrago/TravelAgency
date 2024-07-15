package com.chulos.travelagency.user.application;

import java.util.List;

import com.chulos.travelagency.user.domain.entity.User;
import com.chulos.travelagency.user.domain.service.UserService;

public class GetUsersUseCase {
    private final UserService userService;

    public GetUsersUseCase(UserService userService) {
        this.userService = userService;
    }

    public List<User> execute() {
        return userService.getUsers();
    }
}
