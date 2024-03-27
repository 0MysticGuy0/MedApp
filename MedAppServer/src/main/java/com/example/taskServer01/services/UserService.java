package com.example.taskServer01.services;

import com.example.taskServer01.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    User save(User user);
    User getByEmail(String email);
    void deleteByEmail(String email);
    User updateUser(User user);
}
