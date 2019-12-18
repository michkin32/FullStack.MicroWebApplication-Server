package com.groupfour.chatapp.chatapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Option {

    @Id
    @GeneratedValue
    private Long optionId;

    @NotBlank
    private String optionName;

    @ManyToOne
    private Poll poll;

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }


    public Long getOptionId() {
        return optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
