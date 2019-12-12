package com.groupfour.chatapp.chatapp.message;

import com.groupfour.chatapp.chatapp.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message create(Message message) {
        return messageRepository.save(message);
    }

    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).get();
    }

    //TODO write logic for getMessageByUser()

    public Message updateMessageBody(Long messageId, Message newMessage) {
        Message message = messageRepository.findById(messageId).get();
        message.setMessageBody(newMessage.getMessageBody());
        return messageRepository.save(message);
    }

    public Boolean deleteMessageById(Long messageId) {
        messageRepository.deleteById(messageId);
        return true;
    }
}
