package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.Poll;
import com.groupfour.chatapp.chatapp.models.Vote;
import com.groupfour.chatapp.chatapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VoteService {

    private final PollRepository pollRepository;
    private VoteRepository voteRepository;
    private ChatRepository chatRepository;
    private UserRepository userRepository;
    private OptionRepository optionRepository;

    @Autowired
    public VoteService(PollRepository pollRepository, VoteRepository voteRepository, ChatRepository chatRepository, UserRepository userRepository, OptionRepository optionRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.optionRepository = optionRepository;
    }


    public Vote create(Long chatId, Long pollId, Long userId, Long optionId) {
        Chat chat = chatRepository.findById(chatId).get();
        Poll poll = pollRepository.findById(pollId).get();
        Vote vote = new Vote();
        vote.setVoter(userRepository.findByUserId(userId).get());
        vote.setOption(optionRepository.findById(optionId).get());
        vote = voteRepository.save(vote);

        Set<Vote> votes = poll.getVotes();
        votes.add(vote);
        poll.setVotes(votes);

        pollRepository.save(poll);
        chatRepository.save(chat);
        return vote;
    }

    public Iterable<Vote> findAll() {
        return null;
    }

    public Iterable<Vote> findVotesByPoll(Long pollId) {
        return null;
    }

    // SELECT *
    // FROM chats JOIN polls
    // ON chats.id = polls.id
    public Vote show(Long chatId, Long pollId) {
        return null;
    }
}
