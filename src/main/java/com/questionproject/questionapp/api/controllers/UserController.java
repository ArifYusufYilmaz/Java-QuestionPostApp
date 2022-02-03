package com.questionproject.questionapp.api.controllers;

import com.questionproject.questionapp.dataAccess.UserDao;
import com.questionproject.questionapp.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserDao userDao;
    // Autowired, constructor injection, setter injection gibi yöntemler var.
    public UserController(UserDao userDao){
        this.userDao=userDao;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUSer){
        return userDao.save(newUSer);
    }
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
         // bu user db'mizde olmayabilir.
         // Şimdilik bulamazsa null dönsün.
         // custom exception ekle
        return userDao.findById(userId).orElse(null);
    }
    // Varolan id'li bir user ı değiştirebiliriz
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        // update edilmek istenen kullanıcıya ait id db'de aranır,
        // varsa @RequestBody ile user objesi şeklinde alınan update edilmek istenen veriler set edilir.
        Optional<User> user = userDao.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userDao.save(foundUser);
            return foundUser;
        } else{
            //custom exception ekle
            return null;
        }
   }
   //spesifik bir user için silme isteği,
   @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userDao.deleteById(userId);
    }
}

















