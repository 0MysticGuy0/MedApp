package com.example.taskServer01;

import com.example.taskServer01.models.User;
import com.example.taskServer01.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/med/users") //адрес
@AllArgsConstructor
public class MedController {
    private final UserService userService;

    @RequestMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/save_user")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email){
        return userService.getByEmail(email);
    }

    @PutMapping("/update_user")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete_user/{email}")
    public void deleteUser(@PathVariable String email){
        userService.deleteByEmail(email);
    }
}
