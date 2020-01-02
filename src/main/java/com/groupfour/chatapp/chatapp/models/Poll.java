package com.groupfour.chatapp.chatapp.models;

import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue
    @Column(name = "POLL_ID")
    private Long pollId;


    @Column(name = "QUESTION")
    private String pollQuestion;

    private Date timeStamp = new Date();
    @JoinColumn(name = "POLL_ID")
    @Size(max = 3)
    @OrderBy
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Option> options;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    private Set<Vote> votes;
    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User pollCreator;

    public Poll(String question, Set<Option> options){
        this.pollQuestion = question;
        this.options = options;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }



    public Long getPollId() {
        return pollId;
    }

    public void addOptionToPoll(Option option)    {
        options.add(option);
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
