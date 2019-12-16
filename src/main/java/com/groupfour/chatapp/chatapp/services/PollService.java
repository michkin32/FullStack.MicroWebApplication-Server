package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.models.Option;
import com.groupfour.chatapp.chatapp.models.Poll;
import com.groupfour.chatapp.chatapp.repositories.OptionRepository;
import com.groupfour.chatapp.chatapp.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    private PollRepository pollRepository;
    public OptionRepository optionRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }



    public Iterable<Poll> findAll(Long id) {
        Iterable<Poll> allPolls = pollRepository.findPollsByChat(id);;
        return allPolls;
    }

    public Iterable<Poll> index() {
        return pollRepository.findAll();
    }

    public Poll show(Long id,Long pollId) {
        return pollRepository.findPollByChatAndPoll(id, pollId);
    }

    public Poll getPollById(Long pollId)   {
        return pollRepository.findById(pollId).get();
    }

    public Poll create(Poll poll) {
        return pollRepository.save(poll);
    }

    public Poll addOptionToPoll(Long pollId, Long optionId) {
        Poll poll = getPollById(pollId);
        Option option = optionRepository.findById(optionId).get();
        poll.addOptionToPoll(option);
        return pollRepository.save(poll);
    }


    public Poll update(Long id, Poll newPollData) {
        Poll originalPoll = pollRepository.findById(id).get();
        originalPoll.setPollCreator(newPollData.getPollCreator());
        originalPoll.setOptions(newPollData.getOptions());
        return pollRepository.save(originalPoll);
    }

    public Boolean delete(Long id) {
        pollRepository.deleteById(id);
        return true;
    }
}