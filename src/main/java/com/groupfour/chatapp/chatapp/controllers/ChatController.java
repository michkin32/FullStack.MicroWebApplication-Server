package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.dataprojections.ChatDTO;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.services.ChatService;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ChatController {

    private ChatService chatService;
    private ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatService chatService, ChatRepository chatRepository) {
        this.chatService = chatService;
        this.chatRepository = chatRepository;
    }

    @GetMapping("/chat/user/{userId}")
    public ResponseEntity<Iterable<ChatDTO>> getChatsByUser(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(chatService.getUserChats(userId), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<Chat> findChatBy(@PathVariable Long chatId) {
        try {
            verifyChatById(chatId);
            return new ResponseEntity<>(chatService.getChatById(chatId), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/chat/{adminId}")
    public ResponseEntity<ChatDTO> createNewChat(@RequestBody Chat chatName, @PathVariable Long adminId) {
        Chat chat = chatService.createNewChat(chatName, adminId);
        return new ResponseEntity<>(chatRepository.findByChatId(chat.getChatId()), HttpStatus.CREATED);
    }

    @PutMapping("/chat/{chatId}/user/{username}")
    public ResponseEntity<ChatDTO> addUserToChat(@PathVariable Long chatId, @PathVariable String username) {
        return new ResponseEntity<>(chatService.addUserToChat(chatId, username), HttpStatus.OK);
    }

    @PatchMapping("/chat/{chatId}")
    public ResponseEntity<Chat> updateChatName(@PathVariable Long chatId, @RequestBody String newChatName) {
        try {
            verifyChatById(chatId);
            chatService.updateChatName(chatId, newChatName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/chat/{chatId}/1")
    public ResponseEntity<Chat> updateChatAdmin(@PathVariable Long chatId, @RequestBody Long newAdminId) {
        try {
            verifyChatById(chatId);
            chatService.updateChatAdmin(chatId, newAdminId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/chat/{chatId}")
    public ResponseEntity<Boolean> deleteChat(@PathVariable Long chatId) {
        try {
            verifyChatById(chatId);
            return new ResponseEntity<>(chatService.deleteChatByChatId(chatId), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public void verifyChatById(Long chatId) {
        if (chatRepository.existsById(chatId)) {
            throw new ResourceNotFoundException("Department " + chatId + " not found.");
        }
    }
}
