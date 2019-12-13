package com.groupfour.chatapp.chatapp.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote){
      return voteRepository.save(vote);
    }

    public Iterable<Vote> findAll() {
        return null;
    }

    public Iterable<Vote> findVotesByPoll(Long pollId) {
        return null;
    }
}
