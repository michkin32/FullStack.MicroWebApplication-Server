package com.groupfour.chatapp.chatapp.user;

import com.groupfour.chatapp.chatapp.chat.Chat;
import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private Chat defaultChat;

    private UserRepository userRepository;
    private ChatRepository chatRepository;

    @Autowired
    public UserService(UserRepository userRepository, ChatRepository chatRepository) {

        this.userRepository = userRepository;
        this.chatRepository = chatRepository;

        defaultChat = chatRepository.findById(1L).orElseGet(() -> new Chat("Default"));
    }

    public User createUser(User user) {
        defaultChat.addUserToChat(user);
        chatRepository.save(defaultChat);

        return userRepository.save(user);
    }
}
