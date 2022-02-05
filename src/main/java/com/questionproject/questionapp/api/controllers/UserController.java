package com.questionproject.questionapp.api.controllers;

import com.questionproject.questionapp.business.UserManager;
import com.questionproject.questionapp.dataAccess.UserDao;
import com.questionproject.questionapp.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserManager userManager;
    // Autowired, constructor injection, setter injection gibi yöntemler var.
    public UserController(UserManager userManager){
        this.userManager = userManager;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userManager.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userManager.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userManager.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
         return userManager.updateOneUser(userId, newUser);
   }

   @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
       userManager.deleteById(userId);
    }
}

















