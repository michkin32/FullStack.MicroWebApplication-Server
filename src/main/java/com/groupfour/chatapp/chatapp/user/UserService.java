package com.groupfour.chatapp.chatapp.user;

import com.groupfour.chatapp.chatapp.chat.Chat;
import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.Optional;

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

    public Iterable<Chat> getUserChats(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        return chatRepository.findAllByUsersContains(user);
    }

    public User getUserByName(String name) throws ResourceNotFoundException{
        return userRepository.findByUserName(name).orElseThrow(ResourceNotFoundException::new);
    }
}
