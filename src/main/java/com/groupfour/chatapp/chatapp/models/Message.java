package com.groupfour.chatapp.chatapp.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long messageId;

    private Date timeStamp = new Date();
    private String messageBody;


    private Long senderId;


    private Long chatId;

    public Message() {
    }

    public Message(String messageBody){
        this.messageBody = messageBody;
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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
