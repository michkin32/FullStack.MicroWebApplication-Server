package com.groupfour.chatapp.chatapp.dataprojections;

import com.groupfour.chatapp.chatapp.models.Option;

public interface PollDTO {
    Long getPollId();
    String getPollQuestion();
    Iterable<Option> getOptions();
    Iterable<VoteDTO> getVotes();
    ChatDTO getChat();
    UserDTO getPollCreator();
}
