package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(name = "chat/{id}")
    public ResponseEntity<Chat> findChatBy(@PathVariable Long chatId) {
        try {
            chatService.verifyChat(chatId);
            return new ResponseEntity<>(chatService.getChatById(chatId), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(name = "chat")
    public ResponseEntity<Chat> createNewChat(@RequestBody Chat chat)   {
        return new ResponseEntity<>(chatService.creatNewChat(chat), HttpStatus.CREATED);
    }




}
