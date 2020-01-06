package com.groupfour.chatapp.chatapp.services;


import com.groupfour.chatapp.chatapp.dtos.UserDTO;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;



@Service
public class UserService {
    final private Long defaultChatId = 1L;

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User createUser(UserDTO user) {

        User newUser = new User();
        newUser.setUserName(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public User updateUser(Long id, User user) {
        User updateUser = findUserById(id);
        updateUser.setUserName(user.getUserName());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        return userRepository.save(updateUser);
    }





    public User getUserByName(String name) throws ResourceNotFoundException {
        return userRepository.findByUserName(name).orElseThrow(ResourceNotFoundException::new);
    }

}
