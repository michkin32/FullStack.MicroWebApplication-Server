package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.Utils.HibernateUtil;
import com.groupfour.chatapp.chatapp.models.Friend;
import com.groupfour.chatapp.chatapp.repositories.FriendRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class FriendService {

    UserRepository userRepository;

    FriendRepository friendRepository;

    @Autowired
    public FriendService(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public FriendRepository getFriendRepository() {
        return friendRepository;
    }

    public void setFriendRepository(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }
}