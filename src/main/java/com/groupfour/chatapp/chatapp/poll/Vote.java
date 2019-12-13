package com.groupfour.chatapp.chatapp.poll;

import com.groupfour.chatapp.chatapp.user.User;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    private Long voteId;

    @ManyToOne
    private Option option;

    @OneToOne
    private User voter;

}
