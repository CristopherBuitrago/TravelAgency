package com.chulos.travelagency.auth.application;

import com.chulos.travelagency.auth.domain.service.AuthService;
import com.chulos.travelagency.user.domain.entity.User;

public class RegisterUseCase {
    // attributes
    private final AuthService authService;

    // constructor
    public RegisterUseCase(AuthService authService) {
        this.authService = authService;
    }
    
    // execute method
    public String execute (User user) {
        return authService.register(user);
    }
}