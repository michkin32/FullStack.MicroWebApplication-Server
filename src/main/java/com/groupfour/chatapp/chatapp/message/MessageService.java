package com.groupfour.chatapp.chatapp.message;

import com.groupfour.chatapp.chatapp.chat.Chat;
import com.groupfour.chatapp.chatapp.chat.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private ChatRepository chatRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public Message createMessageByChatId(Long chatId, Message message) {
        message.setDestinationChat(chatRepository.findById(chatId).get());
        return messageRepository.save(message);
    }

    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).get();
    }

    //TODO write logic for getMessagesByUserId()
    public Iterable<Message> getMessagesByUserId(Long senderId) {
        return messageRepository.findMessagesBySender(senderId);
    }

    //TODO write logic for getMessagesByChatId()
    public Iterable<Message> getMessagesByChatId(Long chatId) {
       return messageRepository.findMessagesByDestinationChat(chatId);
    }

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
