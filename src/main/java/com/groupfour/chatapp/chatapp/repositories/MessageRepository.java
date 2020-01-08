package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.dataprojections.MessageDTO;
import com.groupfour.chatapp.chatapp.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Iterable<Message> findMessagesBySender_UserId(Long userId);
    Iterable<MessageDTO> findMessagesByDestinationChat_ChatId(Long chatId);
    Optional<MessageDTO> findByMessageId(Long messageId);
}
