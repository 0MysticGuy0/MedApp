package com.example.taskServer01.services;

import com.example.taskServer01.models.User;
import com.example.taskServer01.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class UserServiceImpl implements UserService{
    private UserRepository repository;
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
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
    @Transactional
    public void deleteByEmail(String email) {
        repository.deleteById(email);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }
}
