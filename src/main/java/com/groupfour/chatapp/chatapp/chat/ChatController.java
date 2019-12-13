package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private ChatService chatService;
    private ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
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

    @GetMapping("/chat/")
    public ResponseEntity<Chat> findChatByName(@PathVariable String chatName) {
        try {
            verifyChatByName(chatName);
            return new ResponseEntity<>(chatService.getChatByName(chatName), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/chat/")
    public ResponseEntity<Iterable<Chat>> getAllChats() {
        return new ResponseEntity<>(chatService.getAllChats(), HttpStatus.OK);
    }

    @PostMapping("/chat")
    public ResponseEntity<Chat> createNewChat(@RequestBody Chat chatName) {
        return new ResponseEntity<>(chatService.creatNewChat(chatName), HttpStatus.CREATED);
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

    @PatchMapping("/chat/{chatId}")
    public ResponseEntity<Chat> updateChatAdmin(@PathVariable Long chatId, @RequestBody Long newAdminId) {
        try {
            verifyChatById(chatId);
            chatService.updateChatAdmin(chatId, newAdminId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/chat/{chatId")
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

    public void verifyChatByName(String chatName) {
        if (chatRepository.findByChatName(chatName).equals(null)) {
            throw new ResourceNotFoundException("Department " + chatName + " not found.");
        }
    }

}
