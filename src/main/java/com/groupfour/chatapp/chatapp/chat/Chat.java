package com.groupfour.chatapp.chatapp.chat;

import com.groupfour.chatapp.chatapp.message.Message;
import com.groupfour.chatapp.chatapp.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Chat {
    @Id
    @GeneratedValue
    private Long chatId;

    private String chatName;

    private Date timeStamp = new Date();

    @ManyToOne
    User admin;

    @ManyToMany
    Set<User> users;

    public Chat() {
    }

    public Chat(String chatName, User admin, Set<User> users){
        this.chatName = chatName;
        this.admin = admin;
        this.users = users;
    }

    public Long getChatId() {
        return chatId;
    }


    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
