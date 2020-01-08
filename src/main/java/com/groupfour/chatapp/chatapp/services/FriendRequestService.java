package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.models.FriendRequest;
import com.groupfour.chatapp.chatapp.repositories.FriendRequestRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {

    FriendRequestRepository friendRequestRepository;
    UserRepository userRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    public FriendRequest createFriendRequestByUserId(Long id, String name) {
        friendRequestRepository.createFriendRequest(name);
        userRepository.findByUserId(id);
        return null;
    }
    public Object getFriendRequestByUserId(Long friendRequestId) {
        return friendRequestRepository.findById(friendRequestId).get();
    }

    public Iterable<FriendRequest> sendFriendRequest(FriendRequest friendRequest, String name){
        Iterable<FriendRequest> sendRequest = friendRequestRepository.sendFriendRequest(friendRequest);
         friendRequestRepository.save(name);
         return sendRequest;
    }
}