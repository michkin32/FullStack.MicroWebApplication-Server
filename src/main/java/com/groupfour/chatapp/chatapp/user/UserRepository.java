package com.groupfour.chatapp.chatapp.user;

import com.groupfour.chatapp.chatapp.chat.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String name);
}
