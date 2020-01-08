package com.groupfour.chatapp.chatapp.models;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    private Long voteId;

    @ManyToOne
    private Option option;

    @OneToOne
    private User voter;

    public Long getVoteId(){
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }
}
