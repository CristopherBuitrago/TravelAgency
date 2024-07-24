package com.chulos.travelagency.auth.application;

import com.chulos.travelagency.auth.domain.entity.Auth;
import com.chulos.travelagency.auth.domain.service.AuthService;

public class LoginUseCase {
    // attributes
    private final AuthService authService;

    // constructor
    public LoginUseCase(AuthService authService) {
        this.authService = authService;
    }

    // execute method
    public String execute (Auth auth) {
        return authService.login(auth);
    }
}