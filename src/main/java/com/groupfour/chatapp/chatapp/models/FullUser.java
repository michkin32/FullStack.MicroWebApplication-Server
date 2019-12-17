package com.groupfour.chatapp.chatapp.models;



import truncatedmodels.TruncatedUser;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class FullUser extends TruncatedUser {

    private String password;
    private String email;
    private Date timeStamp = new Date();

    @OneToMany
    private Set<Message> messages;


    public FullUser() {
        super();
    }

    public FullUser(String userName, String password, String email) {
       super(userName);
       this.password = password;
       this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

}
