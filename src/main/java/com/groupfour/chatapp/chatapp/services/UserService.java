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


@Service("userService")
public class UserService {
    final private Long defaultChatId = 1L;

    private UserRepository userRepository;
    private ChatRepository chatRepository;



    @Autowired
    public UserService(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;


        if (!chatRepository.existsById(defaultChatId)) {
            chatRepository.save(new Chat("Default"));
        }
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        Chat defaultChat = chatRepository.findById(defaultChatId).get();
        defaultChat.addUserToChat(savedUser);
        chatRepository.save(defaultChat);
        return savedUser;
    }

    public Iterable<Chat> getUserChats(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        return chatRepository.findAllByUsersContains(user);
    }

    public User getUserByName(String name) throws ResourceNotFoundException{
        return userRepository.findByUserName(name).orElseThrow(ResourceNotFoundException::new);
    }

//    VVV Password Reset Methods VVV:

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User findUserByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken).orElseThrow(ResourceNotFoundException::new);
    }

    public User findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken).get();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


}
