package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    public Chat getChatById(Long chatId)   {
        return chatRepository.findById(chatId).get();
    }

    public Chat creatNewChat(Chat chat) {
        return chatRepository.save(chat);
    }












    public void verifyChat(Long chatId) {
        if(chatRepository.existsById(chatId))   {
            throw new ResourceNotFoundException("Department " + chatId + " not found.");
        }
    }

}
