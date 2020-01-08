package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.models.FriendRequest;
import com.groupfour.chatapp.chatapp.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PutMapping("/friendRequest/{friendRequestId}")
    public ResponseEntity<FriendRequest> sendFriendRequestToUserId(@PathVariable Long id, @PathVariable String name){
        ResponseEntity<FriendRequest> friendRequestResponseEntity = new ResponseEntity<>(friendRequestService.createFriendRequestByUserId(id,name), HttpStatus.OK);
        return friendRequestResponseEntity;
        }
    @PostMapping("/accept-friends")
    public ResponseEntity<?> acceptFriendRequests(@RequestBody List<Long> friendRequestsIds) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete-friends")
    public ResponseEntity<?> removeFriendRequests(@RequestBody List<Long> friendRequestsIds) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

