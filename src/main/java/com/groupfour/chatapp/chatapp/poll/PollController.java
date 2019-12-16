package com.groupfour.chatapp.chatapp.poll;


import com.groupfour.chatapp.chatapp.chat.ChatService;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PollController {

    private PollService pollService;

    @Autowired
    private ChatService chatService;


    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping(value="/chat/{id}/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls(@PathVariable Long id) {

        return new ResponseEntity<>(pollService.findAllPolls(id), HttpStatus.OK);
    }
    @RequestMapping(value="/chat/{id}/polls/{pollId}", method= RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long id, @PathVariable Long pollId) {

       return new ResponseEntity<>(pollService.show(id, pollId), HttpStatus.OK);
    }

    @RequestMapping(value="/chat/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        pollService.
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
}
