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
    private Chat chat;
    private UserRepository userRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
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


    public Chat creatNewChat(Chat newChat) {
        return chatRepository.save(newChat);
    }

    public Chat updateChatName(Long chatId, String newChatName) {
        chat = getChatById(chatId);
        chat.setChatName(newChatName);
        return chatRepository.save(chat);
    }

    public Chat updateChatAdmin(Long chatId, Long newAdminId)    {
        chat = getChatById(chatId);
        User admin = userRepository.findById(newAdminId).get();
        chat.setAdmin(admin);
        return chatRepository.save(chat);

    }

    public Boolean deleteChatByChatId(Long chatId) {
        chat = getChatById(chatId);
        chatRepository.delete(chat);
        return true;
    }


















}
