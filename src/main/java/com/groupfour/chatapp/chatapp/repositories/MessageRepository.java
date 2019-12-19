package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.models.FullMessage;
import com.groupfour.chatapp.chatapp.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import truncatedmodels.TruncatedMessage;

@Repository
public interface MessageRepository extends CrudRepository<FullMessage, Long> {
//    Iterable<FullMessage> findMessagesBySender_UserId(Long userId);
//    Iterable<FullMessage> findMessagesByDestinationChat_ChatId(Long chatId);
    //edits made replaced Message in diamond brackets
}
