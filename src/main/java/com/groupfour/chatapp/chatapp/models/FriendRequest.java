package com.groupfour.chatapp.chatapp.models;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Component
@Entity
public class FriendRequest {

    public enum FriendStatus {
        online, offline;
    }

    @OneToMany
    private User sender;

    @ManyToOne
    private User recipient;

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
}