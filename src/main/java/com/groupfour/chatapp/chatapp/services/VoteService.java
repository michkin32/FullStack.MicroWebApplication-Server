package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.Poll;
import com.groupfour.chatapp.chatapp.models.Vote;
import com.groupfour.chatapp.chatapp.repositories.ChatRepository;
import com.groupfour.chatapp.chatapp.repositories.PollRepository;
import com.groupfour.chatapp.chatapp.repositories.VoteRepository;
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

    @Autowired
    public VoteService(PollRepository pollRepository, VoteRepository voteRepository, ChatRepository chatRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
        this.chatRepository = chatRepository;
    }


    public Vote create(Long chatId, Long pollId, Vote vote) {
        Chat chat = chatRepository.findById(chatId).get();
        Poll poll = pollRepository.findById(pollId).get();
        vote = voteRepository.save(vote);

        Set<Vote> votes = poll.getVotes();
        votes.add(vote);
        poll.setVotes(votes);
        chat.setPoll(pollId, poll);

        pollRepository.save(poll);
        chatRepository.save(chat);
        return vote;
    }

    public Iterable<Vote> findAll(Long id) {
        return voteRepository.findVotesByPoll(id);
    }

    // SELECT *
    // FROM chats JOIN polls
    // ON chats.id = polls.id
    public Vote show(Long chatId, Long pollId) {
        Chat chat = chatRepository.findById(chatId).get();
        Poll poll = pollRepository.findById(pollId).get();
        return null;
    }

    public Iterable<Vote> findVotesByPoll(Long pollId) {
        return null;
    }

    public Boolean delete(Long id) {
        voteRepository.deleteById(id);
        return true;
    }
}
