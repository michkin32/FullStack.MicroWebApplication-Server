package com.groupfour.chatapp.chatapp.message;


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
        return new ResponseEntity<>(messageService.getMessageById(messageId), HttpStatus.OK);
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<Message> editMessage(@PathVariable Long messageId, @RequestBody Message message) {
        return new ResponseEntity<>(messageService.updateMessageBody(messageId, message), HttpStatus.OK);
    }

//            @DeleteMapping(/messages/{id})
//    deleteMessage()
//
//    @GetMapping(/messages/{id})
//    searchForMessage()
}
