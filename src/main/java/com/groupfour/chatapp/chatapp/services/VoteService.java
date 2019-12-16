package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
}
