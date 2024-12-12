package com.example.repository;

import com.example.entity.User;
import java.util.List;

public interface UserRepository {
    void saveUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
