package com.example.taskServer01.repositories;

import com.example.taskServer01.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserDAO {
    private List<User> users = new ArrayList<>();
    public List<User> getAllUsers(){
        return users;
    }


    public User getByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public void deleteByEmail(String email) {
        try {
            users.remove(getByEmail(email));
        }catch (NullPointerException ex){

        }
    }
    public User updateUser(User user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(user.getEmail())){
                users.set(i,user);
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        users.add(user);
        return user;
    }
}
