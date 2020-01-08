package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.dataprojections.FriendRequestDTO;
import com.groupfour.chatapp.chatapp.models.FriendRequest;
import com.groupfour.chatapp.chatapp.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.util.List;

@RestController
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/friendRequest/sender/{senderName}/receiver/{receiverName}")
    public ResponseEntity<FriendRequestDTO> sendFriendRequestToUserId(@PathVariable String senderName, @PathVariable String receiverName){
        ResponseEntity<FriendRequestDTO> friendRequestResponseEntity = new ResponseEntity<>(friendRequestService.createFriendRequestByUserId(senderName, receiverName), HttpStatus.OK);
        return friendRequestResponseEntity;
        }
    @PostMapping("/friendRequestChoice")
    public ResponseEntity<?> UpdateRequest(@RequestBody FriendRequest friendRequest) {
        return new ResponseEntity<>(friendRequestService.choiceFriendRequest(friendRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete-friends")
    public ResponseEntity<?> removeFriendRequests(@RequestBody List<Long> friendRequestsIds) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

