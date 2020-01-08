package com.groupfour.chatapp.chatapp.models;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

@Entity
public class FriendRequest {

    @GeneratedValue
    @Id
    private Long friendRequestId;

    @ManyToOne
    private User sender;


    @ManyToOne
    private User recipient;

    private boolean friendRequestChoice;


    @Autowired
    public FriendRequest(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public FriendRequest() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Long getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(Long friendRequestId) {
        this.friendRequestId = friendRequestId;
    }

    public boolean isFriendRequestChoice() {
        return friendRequestChoice;
    }

    public void setFriendRequestChoice(boolean friendRequestChoice) {
        this.friendRequestChoice = friendRequestChoice;
    }

}
