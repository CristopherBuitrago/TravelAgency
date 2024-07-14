package com.chulos.travelagency.user.domain.service;

import java.util.List;

import com.chulos.travelagency.user.domain.entity.User;

public interface UserService {
    String createUser(User user);
    User findById(int id);
    List<User> getUsers();
    String updateUser(User user);
    String deleteUser(int id);
}
