package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatRepository chatRepository;
    private Chat chat;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    public Chat getChatById(Long chatId)   {
        return chatRepository.findById(chatId).get();
    }

    public Chat creatNewChat(Chat newChat) {
        return chatRepository.save(newChat);
    }

    public Chat updateChatName(Long chatId, String newChatName) {
        chat = getChatById(chatId);
        chat.setChatName(newChatName);
        return chatRepository.save(chat);
    }

//    public Chat deleteChat(Long chatId) {
//        chat = getChatById(chatId);
//
//    }














    public void verifyChat(Long chatId) {
        if(chatRepository.existsById(chatId))   {
            throw new ResourceNotFoundException("Department " + chatId + " not found.");
        }
    }

}
