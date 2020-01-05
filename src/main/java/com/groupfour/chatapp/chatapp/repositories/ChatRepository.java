package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.dataprojections.ChatDTO;
import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long>{
    Iterable<ChatDTO> findAllByUsersContains(User user);
}
