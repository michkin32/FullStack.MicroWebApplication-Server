package com.groupfour.chatapp.chatapp.poll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Option {

    @Id
    @GeneratedValue
    private Long optionId;

    private String optionName;



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
