package com.groupfour.chatapp.chatapp.controllers;



import com.groupfour.chatapp.chatapp.dtos.UserDTO;

import com.groupfour.chatapp.chatapp.models.User;

import com.groupfour.chatapp.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser (@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/user/get/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.findByUsername(username),HttpStatus.OK);
    }




}
