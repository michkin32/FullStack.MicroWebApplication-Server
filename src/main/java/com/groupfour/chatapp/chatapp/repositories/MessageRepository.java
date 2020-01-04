package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.models.Message;
import com.groupfour.chatapp.chatapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Iterable<Message> findMessagesBySenderId(Long senderId);
    Iterable<Message> findMessagesByDestinationChat_ChatId(Long chatId);

}
