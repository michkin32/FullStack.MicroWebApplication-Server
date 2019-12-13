package com.groupfour.chatapp.chatapp.poll;

import com.groupfour.chatapp.chatapp.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PollService {

    private PollRepository pollRepository;

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

    public Poll create(Poll poll) {
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
