package com.groupfour.chatapp.chatapp.services;


import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    final private Long defaultChatId = 1L;

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;



   @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    These eem like they should be in Chat Repository
//    public User createUser(User user) {
//        User savedUser = userRepository.save(user);
//        Chat defaultChat = chatRepository.findById(defaultChatId).get();
//        defaultChat.addUserToChat(savedUser);
//        chatRepository.save(defaultChat);
//        return savedUser;
//    }
//
//    public Iterable<Chat> getUserChats(Long userId) throws ResourceNotFoundException {
//        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
//        return chatRepository.findAllByUsersContains(user);
//    }

    public User getUserByName(String name) throws ResourceNotFoundException{
        return userRepository.findByUserName(name).orElseThrow(ResourceNotFoundException::new);
    }
    public User getUserById(Long id) throws ResourceNotFoundException{
        return userRepository.findUserById(id);
    }
}
