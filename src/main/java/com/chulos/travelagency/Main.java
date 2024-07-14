package com.chulos.travelagency;

import com.chulos.travelagency.user.application.CreateUserUseCase;
import com.chulos.travelagency.user.domain.service.UserService;
import com.chulos.travelagency.user.infrastructure.in.UserCreateController;
import com.chulos.travelagency.user.infrastructure.out.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        UserCreateController userCreateController = new UserCreateController(createUserUseCase);

        userCreateController.start();
    }
}