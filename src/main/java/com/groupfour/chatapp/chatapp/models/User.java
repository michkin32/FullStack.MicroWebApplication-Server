package com.groupfour.chatapp.chatapp.models;



import com.groupfour.chatapp.chatapp.models.Message;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Embedded
    private UserCredentials userCredentials;

    @Column(name = "user_name")
    private String userName;
    private String email;
    private Date timeStamp = new Date();
    private Integer activeStatus = 0;

    @OneToMany
    private Set<Message> messages;


    protected User() {
    }

    public User(UserCredentials userCredentials)    {
        this.userCredentials = userCredentials;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(userCredentials, user.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userCredentials);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", userCredentials=" + userCredentials +
                '}';
    }


    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }
}
