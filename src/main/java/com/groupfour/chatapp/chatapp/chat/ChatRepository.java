package com.groupfour.chatapp.chatapp.chat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long>{
    public Chat findByChatName(String chatName);





}
