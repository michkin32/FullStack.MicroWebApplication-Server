package com.groupfour.chatapp.chatapp.models;

import truncatedmodels.TruncatedMessage;

import javax.persistence.*;


@MappedSuperclass
public class FullMessage extends TruncatedMessage {
    @ManyToOne
    private User sender;

    @ManyToOne
    private Chat destinationChat;

    public FullMessage() {
        super();
    }

    public FullMessage(String messageBody){
        super(messageBody);
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Chat getDestinationChat() {
        return destinationChat;
    }

    public void setDestinationChat(Chat destinationChat) {
        this.destinationChat = destinationChat;
    }
}

