package com.groupfour.chatapp.chatapp.controllers;



import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import com.groupfour.chatapp.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:4200")
@RestController("userController")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        try {
            return new ResponseEntity(userService.getUserById(userId), HttpStatus.OK);
        }   catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/{userId}/chats")
    public ResponseEntity<Iterable<Chat>> getUserChats(@PathVariable Long userId) {
        try {
            return new ResponseEntity(userService.getUserChats(userId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    //    @PutMapping("/user/{userId}")
//    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
//        User existingUser = userRepository.findById(userId).get();
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(user.getPassword());
//        return new ResponseEntity<>(userRepository.save(existingUser), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/user/{userId}")
//    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
//        userRepository.deleteById(userId);
//        return new ResponseEntity<>(true, HttpStatus.OK);
//    }


//    -------------------------- Login Methods ------------------------------

    @GetMapping("/user/{userName}/login")
    public ResponseEntity<User> loginUser(@PathVariable String userName) {
        try {
            return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return
                user.getUserName().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }



}
