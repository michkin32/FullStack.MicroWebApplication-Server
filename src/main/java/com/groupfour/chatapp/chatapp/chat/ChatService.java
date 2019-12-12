package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
}
