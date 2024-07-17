package com.chulos.travelagency.auth.infrastructure.out;

import com.chulos.travelagency.auth.domain.entity.Auth;
import com.chulos.travelagency.auth.domain.service.AuthService;
import com.chulos.travelagency.user.domain.entity.User;

public class AuthRepository implements AuthService{

    @Override
    public String login(Auth auth) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public String register(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
    
}
