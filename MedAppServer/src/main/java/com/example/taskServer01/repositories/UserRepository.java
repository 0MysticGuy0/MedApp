package com.example.taskServer01.repositories;

import com.example.taskServer01.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //необязательно
public interface UserRepository extends JpaRepository<User,String> {
    User getByEmail(String email);
}
