package com.chulos.travelagency;

import com.chulos.travelagency.user.application.CreateUserUseCase;
import com.chulos.travelagency.user.application.DeleteUserUseCase;
import com.chulos.travelagency.user.application.FindUserUseCase;
import com.chulos.travelagency.user.application.GetUsersUseCase;
import com.chulos.travelagency.user.application.UpdateUserUseCase;
import com.chulos.travelagency.user.domain.service.UserService;
import com.chulos.travelagency.user.infrastructure.in.CreateUserController;
import com.chulos.travelagency.user.infrastructure.in.DeleteUserController;
import com.chulos.travelagency.user.infrastructure.in.FindUserController;
import com.chulos.travelagency.user.infrastructure.in.GetUsersController;
import com.chulos.travelagency.user.infrastructure.in.UpdateUserController;
import com.chulos.travelagency.user.infrastructure.out.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserRepository();

        //create user testing
        //CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        //CreateUserController createUserController = new CreateUserController(createUserUseCase);
        //createUserController.start();

        //delete user testing
        //DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userService);
        //DeleteUserController deleteUserController = new DeleteUserController(deleteUserUseCase);
        //deleteUserController.start();

        //find user testing
        //FindUserUseCase findUserUseCase = new FindUserUseCase(userService);
        //FindUserController findUserController = new FindUserController(findUserUseCase);
        //findUserController.start();

        //get users testing
        //GetUsersUseCase getUsersUseCase = new GetUsersUseCase(userService);
        //GetUsersController getUsersController = new GetUsersController(getUsersUseCase);
        //getUsersController.start();

        //update user testing
        //UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userService);
        //UpdateUserController updateUserController = new UpdateUserController(updateUserUseCase);
        //updateUserController.start();
    }
}