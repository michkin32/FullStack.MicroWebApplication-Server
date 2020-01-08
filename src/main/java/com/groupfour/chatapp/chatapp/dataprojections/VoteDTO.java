package com.groupfour.chatapp.chatapp.dataprojections;

import com.groupfour.chatapp.chatapp.models.Option;

public interface VoteDTO {
    Long getVoteId();
    Option getOption();
    UserDTO getVoter();
}
