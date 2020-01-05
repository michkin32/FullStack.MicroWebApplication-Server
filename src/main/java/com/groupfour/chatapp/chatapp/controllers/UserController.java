package com.groupfour.chatapp.chatapp.controllers;



import com.groupfour.chatapp.chatapp.dataprojections.ChatDTO;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import com.groupfour.chatapp.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

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

    @PatchMapping("/user/{userId}")
    public ResponseEntity<User> patchUser(@PathVariable Long userId, @RequestBody User user){
        User existingUser = userRepository.findById(userId).get();
        existingUser.setProfilePic(user.getProfilePic());
        User updatedUser = userRepository.save(existingUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
