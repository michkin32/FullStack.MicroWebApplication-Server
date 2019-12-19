package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.models.FullMessage;
import com.groupfour.chatapp.chatapp.models.Message;
import com.groupfour.chatapp.chatapp.services.FullMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import truncatedmodels.TruncatedMessage;

@RestController
public class FullMessageController {

    private FullMessageService fullMessageService;

    @Autowired
    public FullMessageController(FullMessageService fullMessageService) {
        this.fullMessageService = fullMessageService;
    }

    @PostMapping("user/{userId}/chat/{chatId}/message")
    public ResponseEntity<TruncatedMessage> createMessageInChat(@PathVariable Long userId, @PathVariable Long chatId, @RequestBody FullMessage message) {
        return new ResponseEntity<>(fullMessageService.createMessageByChatId(userId, chatId, message), HttpStatus.CREATED);
    }
//
//    @GetMapping("/chat/{chatId}/messages")
//    public ResponseEntity<Iterable<Message>> getMessagesByChatId(@PathVariable Long chatId) {
//        return new ResponseEntity<>(fullMessageService.getMessagesByChatId(chatId), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/user/{senderId}/messages")
//    public ResponseEntity<Iterable<Message>> getMessagesByUserId(@PathVariable Long senderId) {
//        return new ResponseEntity<>(fullMessageService.getMessagesByUserId(senderId), HttpStatus.CREATED);
//    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<TruncatedMessage> getMessage(@PathVariable Long messageId) {
        try {
            verifyMessage(messageId);
            return new ResponseEntity<>(fullMessageService.getMessageById(messageId), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/messages/{messageId}")
//    public ResponseEntity<Message> editMessage(@PathVariable Long messageId, @RequestBody Message message) {
//        try {
//            verifyMessage(messageId);
//            return new ResponseEntity<>(fullMessageService.updateMessageBody(messageId, message), HttpStatus.OK);
//        } catch (ResourceNotFoundException ex) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("messages/{messageId")
//    public ResponseEntity<Boolean> deleteMessage(@PathVariable Long messageId) {
//        try {
//            verifyMessage(messageId);
//            return new ResponseEntity<>(fullMessageService.deleteMessageById(messageId), HttpStatus.OK);
//        } catch (ResourceNotFoundException ex) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    private void verifyMessage(Long messageId) {
        if (!fullMessageService.messageExists(messageId)) {
            throw new ResourceNotFoundException("Message " + messageId + " not found.");
        }
    }
}
