package com.groupfour.chatapp.chatapp.services;


import com.groupfour.chatapp.chatapp.models.FullMessage;
import com.groupfour.chatapp.chatapp.models.Message;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.repositories.MessageRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truncatedmodels.TruncatedMessage;

@Service
public class FullMessageService {

    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private UserRepository userRepository;

    @Autowired
    public FullMessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public TruncatedMessage createMessageByChatId(Long userId, Long chatId, FullMessage message) {
        message.setDestinationChat(chatRepository.findById(chatId).get());
        message.setSender(userRepository.findById(userId).get());
        messageRepository.save(message);
        return new TruncatedMessage(message.getMessageBody());
    }

    public TruncatedMessage getMessageById(Long messageId) {
        FullMessage fullMessage = messageRepository.findById(messageId).get();
        return new TruncatedMessage(fullMessage.getMessageBody());
    }

//    public Iterable<TruncatedMessage> getMessagesByUserId(Long senderId) {
//        return messageRepository.findMessagesBySender_UserId(senderId);
//    }

//    public Iterable<TruncatedMessage> getMessagesByChatId(Long chatId) {
//       return messageRepository.findMessagesByDestinationChat_ChatId(chatId);
//    }
//
//    public TruncatedMessage updateMessageBody(Long messageId, Message newMessage) {
//        Message message = messageRepository.findById(messageId).get();
//        message.setMessageBody(newMessage.getMessageBody());
//        return messageRepository.save(message);
//    }
//
//    public Boolean deleteMessageById(Long messageId) {
//        messageRepository.deleteById(messageId);
//        return true;
//    }

    public Boolean messageExists(Long messageId) {
        if(messageRepository.existsById(messageId))
            return true;
        else
            return false;
    }

}
