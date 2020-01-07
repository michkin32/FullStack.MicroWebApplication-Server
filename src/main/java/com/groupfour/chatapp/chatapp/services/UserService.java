package com.groupfour.chatapp.chatapp.services;


import com.groupfour.chatapp.chatapp.dtos.UserDTO;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Optional;


@Service
public class UserService {
    final private Long defaultChatId = 1L;

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User createUser(UserDTO user) throws ValidationException {
        String userName = user.getUsername();
        if (userRepository.existsByUserName(userName))  {
            throw new ValidationException("Username already exists");
        }
        String password = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User newUser = new User();

        newUser.setUserName(userName);
        newUser.setPassword(encodedPassword);
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public User updateUser(Long id, User user) {
        User updateUser = findUserById(id);
        updateUser.setUserName(user.getUserName());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        return userRepository.save(updateUser);
    }

    public void deleteUserById(Long id) {
         userRepository.deleteById(id);
    }



    public Optional<User> getUserByName(String name) throws ResourceNotFoundException {
        return userRepository.findByUserName(name);
    }

}
