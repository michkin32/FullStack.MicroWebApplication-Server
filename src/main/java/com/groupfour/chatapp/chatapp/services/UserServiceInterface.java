package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.models.UserDto;

import java.util.List;

public interface UserServiceInterface {

    // I'm not sure that this class is necessary. It might be needed specifically for user login storage.


    User save(UserDto user);

    List<User> findAll();

    void delete(long id);

    User findOne(String username);

    User findById(Long id);

}
