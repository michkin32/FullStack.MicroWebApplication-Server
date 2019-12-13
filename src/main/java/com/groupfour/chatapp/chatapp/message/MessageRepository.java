package com.groupfour.chatapp.chatapp.message;

import com.groupfour.chatapp.chatapp.chat.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Iterable<Message> findMessagesBySender(Long userId);
    Iterable<Message> findMessagesByDestinationChat(Long chatId);
}
