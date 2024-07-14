package com.chulos.travelagency.user.domain.service;

import java.util.List;

import com.chulos.travelagency.user.domain.entity.User;

public interface UserService {
    void createUser(User user);
    User findById(int id);
    List<User> getUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
