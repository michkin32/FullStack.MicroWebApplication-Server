package com.groupfour.chatapp.chatapp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
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
