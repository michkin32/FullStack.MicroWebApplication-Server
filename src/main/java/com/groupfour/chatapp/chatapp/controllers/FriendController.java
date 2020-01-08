//package com.groupfour.chatapp.chatapp.controllers;
//
//
//import com.groupfour.chatapp.chatapp.models.Friend;
//import com.groupfour.chatapp.chatapp.services.FriendService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//public class FriendController {
//
//    @Autowired
//    private FriendService friendService;
//
//    /* Get friends */
//    @GetMapping("/friends")
//    public List<Friend> getFriends() {
//        return friendService.list();
//    }
//
//    /* Create friends */
//    @PostMapping(value = "/friends")
//    public ResponseEntity<Friend> createFriends(@RequestBody Friend friend) {
//        if (friend == null) {
//            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        } else {
//            friendService.create(friend);
//        }
//        return new ResponseEntity<>(friend, HttpStatus.OK);
//    }
//
//    /* Delete friends */
//    @DeleteMapping("/friends/{id}")
//    public ResponseEntity<Friend> deleteFriends(@PathVariable String id) {
//        if (null == friendService.delete(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    /* Update friends */
//    @PutMapping("/friends/{id}")
//    public ResponseEntity<Friend> updateFriends(
//            @PathVariable("id") String custId, @RequestBody Friend friend) {
//        Friend friendById = friendService.findFriend(custId);
//        if (friendById == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        friendById.setFirstName(friend.getFirstName());
//        friendById.setLastName(friend.getLastName());
//        friendService.update(friendById);
//        return new ResponseEntity<>(friend, HttpStatus.OK);
//    }
//
//    /* Get friends By Id */
//    @GetMapping("/friends/{id}")
//    public ResponseEntity<Friend> getFriends(@PathVariable("id") String id) {
//        Friend friend = friendService.get(id);
//        if (friend == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(friend, HttpStatus.OK);
//    }
//
//    /* Get friends By Name */
//    @GetMapping("/friends")
//    public List<Friend> findByQuery(
//            @RequestParam(value = "first", required = false) String firstName,
//            @RequestParam(value = "last", required = false) String lastName) {
//
//        List<Friend> friend = null;
//
//        if ( firstName != null && !firstName.equalsIgnoreCase("") &&
//                lastName != null && !lastName.equalsIgnoreCase("")	){
//            friend = friendService.findFirstAndLastName(firstName, lastName);
//        } else if (firstName != null && !firstName.equalsIgnoreCase("")) {
//            friend = friendService.findFirstName(firstName);
//        } else if ( lastName != null && !lastName.equalsIgnoreCase("")) {
//            friend = friendService.findLastName(lastName);
//        }
//
//        return friend;
//    }
//}
