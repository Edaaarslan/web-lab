package com.example.service;

import com.example.entity.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}