package com.groupfour.chatapp.chatapp.message;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Boolean messageExists(Long messageId) {
        if(messageRepository.existsById(messageId))
            return true;
        else
            return false;
    }

}
