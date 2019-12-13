package com.groupfour.chatapp.chatapp.message;

import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
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

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageService.create(message), HttpStatus.CREATED);
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
