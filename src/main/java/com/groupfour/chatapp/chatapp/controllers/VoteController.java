package com.groupfour.chatapp.chatapp.controllers;


import com.groupfour.chatapp.chatapp.models.Vote;
import com.groupfour.chatapp.chatapp.services.PollService;
import com.groupfour.chatapp.chatapp.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * controller is responsible for handling
 *      - incoming requests
 *      - outgoing responses
 *      - building response-headers
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VoteController {
    private final PollService pollService;
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService, PollService pollService) {
        this.voteService = voteService;
        this.pollService = pollService;
    }

    @RequestMapping(value = "/chats/{chatId}/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<Vote> createVote(
            @PathVariable Long chatId,
            @PathVariable Long pollId,
            @RequestBody Vote vote) {
        vote = voteService.create(chatId, pollId, vote);
        HttpHeaders responseHeaders = new HttpHeaders(); // Set the headers for the newly created resource
        responseHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getVoteId())
                .toUri());
        return new ResponseEntity<>(vote, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/chats/{chatId}/polls/{pollId}/votes", method = RequestMethod.GET)
    public ResponseEntity<Vote> getVote(
            @PathVariable Long chatId,
            @PathVariable Long pollId) {
        Vote vote = voteService.show(chatId, pollId);
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/votes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes() {
        return voteService.findAll();
    }


}
