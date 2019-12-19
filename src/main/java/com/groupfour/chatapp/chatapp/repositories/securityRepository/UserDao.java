package com.groupfour.chatapp.chatapp.repositories.securityRepository;

import com.groupfour.chatapp.chatapp.models.securityModel.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    UserDao findByUsername(String username);
}