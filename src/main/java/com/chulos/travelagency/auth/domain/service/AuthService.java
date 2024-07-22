package com.chulos.travelagency.auth.domain.service;

import com.chulos.travelagency.auth.domain.entity.Auth;
import com.chulos.travelagency.user.domain.entity.User;

public interface AuthService {
    String login (Auth auth);
    String register (User user);
}