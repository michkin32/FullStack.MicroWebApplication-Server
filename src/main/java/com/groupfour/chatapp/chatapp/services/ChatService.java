package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.dataprojections.ChatDTO;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatRepository chatRepository;
    private UserRepository userRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Chat getChatById(Long chatId)   {
        return chatRepository.findById(chatId).get();
    }

    public Iterable<ChatDTO> getUserChats(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        return chatRepository.findAllByUsersContains(user);
    }

    public Chat createNewChat(Chat newChat, Long adminId) {
        User admin = userRepository.findById(adminId).get();
        newChat.setAdmin(admin);
        return chatRepository.save(newChat);
    }

    public ChatDTO addUserToChat(Long chatId, String userName) {
        Chat chat = getChatById(chatId);
        User user = userRepository.findByUserName(userName).get();
        chat.addUserToChat(user);
        chatRepository.save(chat);
        return chatRepository.findByChatId(chatId);
    }

    public Chat updateChatName(Long chatId, String newChatName) {
        Chat chat = getChatById(chatId);
        chat.setChatName(newChatName);
        return chatRepository.save(chat);
    }

    public Chat updateChatAdmin(Long chatId, Long newAdminId)    {
        Chat chat = getChatById(chatId);
        User admin = userRepository.findById(newAdminId).get();
        chat.setAdmin(admin);
        return chatRepository.save(chat);

    }

    public Boolean deleteChatByChatId(Long chatId) {
        Chat chat = getChatById(chatId);
        chatRepository.delete(chat);
        return true;
    }


}
