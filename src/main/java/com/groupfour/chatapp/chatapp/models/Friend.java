package com.groupfour.chatapp.chatapp.models;

import javax.persistence.*;
import java.util.List;


public class Friend{

    String firstName;

    String lastName;

    @OneToOne
    User user;

    @OneToMany
    List<Friend> friends;

    @Enumerated
    OnlineStatus status;



    public Friend(User user, List<Friend> friends, OnlineStatus status) {
        this.user = user;
        this.friends = friends;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}