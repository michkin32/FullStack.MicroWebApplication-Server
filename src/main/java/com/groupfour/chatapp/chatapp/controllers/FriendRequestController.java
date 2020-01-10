package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.dataprojections.FriendRequestDTO;
import com.groupfour.chatapp.chatapp.models.FriendRequest;
import com.groupfour.chatapp.chatapp.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO - Modify `CrossOrigin`
@CrossOrigin("*") // allow any front-end application to hit this port
@RestController
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/friendRequest/sender/{senderName}/receiver/{receiverName}")
    public ResponseEntity<FriendRequestDTO> sendFriendRequestToUserId(@PathVariable String senderName, @PathVariable String receiverName){
        try{
        ResponseEntity<FriendRequestDTO> friendRequestResponseEntity = new ResponseEntity<>(friendRequestService.createFriendRequestByUserId(senderName, receiverName), HttpStatus.OK);
        return friendRequestResponseEntity;
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/friendRequestChoice")
    public ResponseEntity<?> UpdateRequest(@RequestBody FriendRequest friendRequest) {
        try {
            return new ResponseEntity<>(friendRequestService.choiceFriendRequest(friendRequest), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-friends")
    public ResponseEntity<?> removeFriendRequests(@RequestBody FriendRequest friendRequests) {
        try {
            friendRequestService.removeFriendRequest(friendRequests);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}