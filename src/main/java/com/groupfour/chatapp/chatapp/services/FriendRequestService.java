package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.dataprojections.FriendRequestDTO;
import com.groupfour.chatapp.chatapp.models.FriendRequest;
import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.repositories.FriendRequestRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {

    @Autowired
    FriendRequestRepository friendRequestRepository;

    @Autowired
    UserRepository userRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    public FriendRequestDTO createFriendRequestByUserId(String sendName, String recieverName) {
        User userSender = userRepository.findByUserName(sendName).get();
        User userReciever = userRepository.findByUserName(recieverName).get();

        FriendRequest newRequest = new FriendRequest(userSender, userReciever);
        newRequest = friendRequestRepository.save(newRequest);
        userReciever.addFriendRequest(newRequest);
        userRepository.save(userReciever);
        FriendRequestDTO friendRequestDTO = friendRequestRepository.findByFriendRequestId(newRequest.getFriendRequestId());
        return friendRequestDTO;
    }

    public Object getFriendRequestByUserId(Long friendRequestId) {
        return friendRequestRepository.findById(friendRequestId).get();
    }

    public FriendRequest sendFriendRequest(FriendRequest friendRequest, String name){
        //Iterable<FriendRequest> sendRequest = friendRequestRepository.sendFriendRequest(friendRequest);
         return friendRequestRepository.save(friendRequest);
    }

    public String choiceFriendRequest(FriendRequest friendRequest){
        User updatedUser1 = userRepository.findByUserId(friendRequest.getSender().getUserId()).get();
        User updatedUser2 = userRepository.findByUserId(friendRequest.getRecipient().getUserId()).get();

        if (friendRequest.isFriendRequestChoice()){
            updatedUser1.addFriends(updatedUser2);
            updatedUser2.addFriends(updatedUser1);
            updatedUser2.deleteFriendRequest(friendRequest);
            userRepository.save(updatedUser1);
            userRepository.save(updatedUser2);
            friendRequestRepository.delete(friendRequest);
        }else{
            updatedUser2.deleteFriendRequest(friendRequest);
            friendRequestRepository.delete(friendRequest);
        }
        return "success";
    }

    public void removeFriendRequest(FriendRequest friendRequests) {
            friendRequestRepository.delete(friendRequests);
    }
}