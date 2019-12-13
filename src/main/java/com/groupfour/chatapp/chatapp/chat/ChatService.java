package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.user.User;
import com.groupfour.chatapp.chatapp.user.UserRepository;
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

    public Chat getChatByName(String chatName)  {
        return chatRepository.findByChatName(chatName);
    }

    public Iterable<Chat> getAllChats()   {
        return chatRepository.findAll();
    }


    public Chat createNewChat(Chat newChat, Long adminId) {
        User admin = userRepository.findById(adminId).get();
        newChat.setAdmin(admin);
        return chatRepository.save(newChat);
    }

    public Chat addUserToChat(Long chatId, Long userId) {
        Chat chat = getChatById(chatId);
        User user = userRepository.findById(userId).get();
        chat.addUserToChat(user);
        return chatRepository.save(chat);
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
