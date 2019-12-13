package com.groupfour.chatapp.chatapp.poll;

import com.groupfour.chatapp.chatapp.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Poll {


    @Id
    @GeneratedValue
    private Long pollId;

    private String pollQuestion;

    private Date timeStamp;

    @OneToMany
    private Set<Option> options;

    @OneToMany
    private Set<Vote> votes;

    @ManyToOne
    private User pollCreator;


    public Long getPollId() {
        return pollId;
    }


    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public User getPollCreator() {
        return pollCreator;
    }

    public void setPollCreator(User pollCreator) {
        this.pollCreator = pollCreator;
    }
}
