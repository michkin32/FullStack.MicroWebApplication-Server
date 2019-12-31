package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ChatRepository extends CrudRepository<Chat, Long>{
    Chat findByChatName(String chatName);
    Iterable<Chat> findAllByUsersContains(User user);
}
