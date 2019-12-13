package com.groupfour.chatapp.chatapp.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Iterable<Message> findMessagesBySender(Long userId);
    Iterable<Message> findMessagesByDestinationChat(Long chatId);
}
