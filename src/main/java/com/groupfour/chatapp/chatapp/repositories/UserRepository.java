package com.groupfour.chatapp.chatapp.repositories;


import com.groupfour.chatapp.chatapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    Optional<User> findByUserName(String username);
}
