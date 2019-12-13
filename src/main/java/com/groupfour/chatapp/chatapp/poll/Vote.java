package com.groupfour.chatapp.chatapp.poll;

import com.groupfour.chatapp.chatapp.user.User;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    private Option option;

    @ManyToOne
    private Poll poll;

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
