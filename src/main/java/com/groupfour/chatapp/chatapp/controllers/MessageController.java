package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.services.MessageService;
import com.groupfour.chatapp.chatapp.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("user/{userId}/chat/{chatId}/message")
    public ResponseEntity<Message> createMessageInChat(@PathVariable Long userId, @PathVariable Long chatId, @RequestBody Message message) {
        return new ResponseEntity<>(messageService.createMessageByChatId(userId, chatId, message), HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}/messages")
    public ResponseEntity<Iterable<Message>> getMessagesByChatId(@PathVariable Long chatId) {
        return new ResponseEntity<>(messageService.getMessagesByChatId(chatId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{senderId}/messages")
    public ResponseEntity<Iterable<Message>> getMessagesByUserId(@PathVariable Long senderId) {
        return new ResponseEntity<>(messageService.getMessagesByUserId(senderId), HttpStatus.CREATED);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Long messageId) {
        try {
            verifyMessage(messageId);
            return new ResponseEntity<>(messageService.getMessageById(messageId), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<Message> editMessage(@PathVariable Long messageId, @RequestBody Message message) {
        try {
            verifyMessage(messageId);
            return new ResponseEntity<>(messageService.updateMessageBody(messageId, message), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("messages/{messageId")
    public ResponseEntity<Boolean> deleteMessage(@PathVariable Long messageId) {
        try {
            verifyMessage(messageId);
            return new ResponseEntity<>(messageService.deleteMessageById(messageId), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private void verifyMessage(Long messageId) {
        if (!messageService.messageExists(messageId)) {
            throw new ResourceNotFoundException("Message " + messageId + " not found.");
        }
    }
}
