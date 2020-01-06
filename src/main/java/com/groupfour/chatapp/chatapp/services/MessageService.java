package com.groupfour.chatapp.chatapp.services;


import com.groupfour.chatapp.chatapp.models.Message;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.repositories.MessageRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Message createMessageByChatId(Long userId, Long chatId, Message message) {
        message.setDestinationChat(chatRepository.findById(chatId).get());
        message.setSender(userRepository.findById(userId).get());
        return messageRepository.save(message);
    }

    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).get();
    }

//    public Iterable<Message> getMessagesByUserId(Long senderId) {
//        return messageRepository.findMessagesBySenderId(senderId);
//    }

    public Iterable<Message> getMessagesByChatId(Long chatId) {
       return messageRepository.findMessagesByDestinationChat_ChatId(chatId);
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
