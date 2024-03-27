package com.example.taskServer01.services;

import com.example.taskServer01.models.User;
import com.example.taskServer01.repositories.InMemoryUserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryUserService implements UserService{

    private InMemoryUserDAO repository;
    public List<User> getAllUsers(){
        return repository.getAllUsers();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        return repository.updateUser(user);
    }
}
