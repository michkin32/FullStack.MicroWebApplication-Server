package com.groupfour.chatapp.chatapp.services;


import com.groupfour.chatapp.chatapp.dataprojections.ChatDTO;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
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

    public Iterable<ChatDTO> getUserChats(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        Iterable<Chat> chats = chatRepository.findAllByUsersContains(user);
        HashSet<ChatDTO> chatDTOS = new HashSet<>();
        for (Chat c : chats) {
            chatDTOS.add(new ChatDTO(c));
        }
        return chatDTOS;
    }

    public User getUserByName(String name) throws ResourceNotFoundException{
        return userRepository.findByUserName(name).orElseThrow(ResourceNotFoundException::new);
    }
    public User getUserById(Long id) throws ResourceNotFoundException{
        return userRepository.findByUserId(id).orElseThrow(ResourceNotFoundException::new);
    }
}
