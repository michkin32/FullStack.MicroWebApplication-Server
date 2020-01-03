package com.groupfour.chatapp.chatapp.controllers;



import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;

import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.models.UserDto;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import com.groupfour.chatapp.chatapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController {


    private UserService userService;
    private UserRepository userRepository;



    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto userDto)  {
        return userRepository.save(userDto);
    }

    @GetMapping("/user")
    public Iterable<User> listUser() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    Seems like it should be in chats
//    @GetMapping("/user/{userId}/chats")
//    public ResponseEntity<Iterable<Chat>> getUserChats(@PathVariable Long userId) {
//        try {
//            return new ResponseEntity<>(userService.getUserChats(userId), HttpStatus.OK);
//        } catch (ResourceNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userName}/login")
    public ResponseEntity<User> loginUser(@PathVariable String userName) {
        try {
            return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User existingUser = userRepository.findById(userId).get();
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return new ResponseEntity<>(userRepository.save(existingUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }



}