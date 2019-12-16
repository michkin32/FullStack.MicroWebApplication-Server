package com.groupfour.chatapp.chatapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long messageId;

    private Date timeStamp = new Date();

    private String messageBody;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Chat destinationChat;

    public Message() {
    }

    public Message(String messageBody, User sender, Chat destinationChat){
        this.messageBody = messageBody;
        this.sender = sender;
        this.destinationChat = destinationChat;
    }

    public Long getMessageId() {
        return messageId;
    }


    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
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
