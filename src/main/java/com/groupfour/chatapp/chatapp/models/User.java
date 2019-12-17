package com.groupfour.chatapp.chatapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupfour.chatapp.chatapp.models.Message;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column
    private String userName;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set roles;


private Date timeStamp=new Date();
private Integer activeStatus=0;


@OneToMany
private Set<Message> messages;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public User(){ }

public User(String userName,String password,String email){
        this.userName=userName;
        this.password=password;
        this.email=email;
        }


public Long getUserId(){
        return userId;
        }

public String getUserName(){
        return userName;
        }

public void setUserName(String userName){
        this.userName=userName;
        }

public String getPassword(){
        return password;
        }

public void setPassword(String password){
        this.password=password;
        }

public String getEmail(){
        return email;
        }

public void setEmail(String email){
        this.email=email;
        }

public Date getTimeStamp(){
        return timeStamp;
        }

public void setTimeStamp(Date timeStamp){
        this.timeStamp=timeStamp;
        }

public Set<Message> getMessages(){
        return messages;
        }

public void setMessages(Set<Message> messages){
        this.messages=messages;
        }

public Integer getActiveStatus(){
        return activeStatus;
        }

public void setActiveStatus(Integer activeStatus){
        this.activeStatus=activeStatus;
        }
        }
