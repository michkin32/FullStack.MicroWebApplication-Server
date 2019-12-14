package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long>{
    Chat findByChatName(String chatName);
    Iterable<Chat> findAllByUsersContains(User user);
}
