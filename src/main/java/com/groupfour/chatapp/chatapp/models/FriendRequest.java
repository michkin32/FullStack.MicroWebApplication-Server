package com.groupfour.chatapp.chatapp.models;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.List;

@Entity
public class FriendRequest {

    @GeneratedValue
    @Id
    private Long FriendRequestId;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    private List<Friends> friends;

    @Autowired
    public FriendRequest(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
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
        return FriendRequestId;
    }

    public void setFriendRequestId(Long friendRequestId) {
        FriendRequestId = friendRequestId;
    }

}
