package com.groupfour.chatapp.chatapp.repositories;


import com.groupfour.chatapp.chatapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends CrudRepository<User, Long> {


    Optional<User> findByUserName(String name);

    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmationToken(String confirmationToken);
    Optional<User> findByResetToken(String resetToken);

}
