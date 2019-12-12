package com.groupfour.chatapp.chatapp.models.polls;

import com.groupfour.chatapp.chatapp.models.User;

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
