package com.groupfour.chatapp.chatapp.repositories;


import com.groupfour.chatapp.chatapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String name);
    Optional<User> findByUserId(Long id);
    Boolean existsByUserName(String userName);
}
