package com.groupfour.chatapp.chatapp.controllers;


import com.groupfour.chatapp.chatapp.models.Poll;
import com.groupfour.chatapp.chatapp.repositories.PollRepository;
import com.groupfour.chatapp.chatapp.services.PollService;
import com.groupfour.chatapp.chatapp.services.ChatService;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PollController {

    private PollService pollService;
    private PollRepository pollRepository;

    @Autowired
    private ChatService chatService;


    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/poll/{pollId}")
    public ResponseEntity<Poll> getPollByPollId(Long pollId) {
        try {
            verifyPollById(pollId);
            return new ResponseEntity<>(pollService.getPollById(pollId), HttpStatus.OK);
        }   catch (ResourceNotFoundException ex)    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/poll/{pollId}")
    public ResponseEntity<Poll> addOptionToPoll(Long pollId, Long optionId) {
        try {
            verifyPollById(pollId);
            return new ResponseEntity<>(pollService.addOptionToPoll(pollId, optionId), HttpStatus.OK);
        }   catch (ResourceNotFoundException ex)    {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/chat/{id}/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls(@PathVariable Long id) {

        return new ResponseEntity<>(pollService.findAll(id), HttpStatus.OK);
    }
    @RequestMapping(value="/chat/{id}/polls/{pollId}", method= RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long id, @PathVariable Long pollId) {

       return new ResponseEntity<>(pollService.show(id, pollId), HttpStatus.OK);
    }

    @RequestMapping(value="/chat/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollService.create(poll);
        return new ResponseEntity<>(pollService.create(poll), HttpStatus.CREATED);
    }

    @RequestMapping(value="/chat/{id}/polls", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@Valid @RequestBody Poll poll,@PathVariable Long id, @PathVariable Long pollId) {
        return new ResponseEntity<>(pollService.update(pollId,poll), HttpStatus.CREATED);
    }
    @RequestMapping(value="/chat/{id}/polls", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll( @PathVariable Long id) {
        try {
            pollService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ResourceNotFoundException ex){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public void verifyPollById(Long pollId) {
        if(pollRepository.existsById(pollId))  {
            throw new ResourceNotFoundException("Poll " + pollId + " not found.");
        }
    }
}