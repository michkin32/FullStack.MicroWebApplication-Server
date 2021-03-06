package com.groupfour.chatapp.chatapp.controllers;


import com.groupfour.chatapp.chatapp.dataprojections.PollDTO;
import com.groupfour.chatapp.chatapp.models.Poll;
import com.groupfour.chatapp.chatapp.repositories.PollRepository;
import com.groupfour.chatapp.chatapp.services.PollService;
import com.groupfour.chatapp.chatapp.services.ChatService;
import com.groupfour.chatapp.chatapp.exceptions.ResourceNotFoundException;
import com.groupfour.chatapp.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class PollController {

    private PollService pollService;
    private PollRepository pollRepository;


    private ChatService chatService;

    @Autowired
    private UserService userService;


    @Autowired
    public PollController(PollService pollService, ChatService chatService) {
        this.chatService = chatService;
        this.pollService = pollService;
    }

    @GetMapping(value="/chat/{id}/polls")
    public ResponseEntity<Iterable<PollDTO>> getAllPolls(@PathVariable Long id) {

        return new ResponseEntity<>(pollService.getPollByChatId(id), HttpStatus.OK);
    }


    @RequestMapping(value="/chat/{chatId}/polls", method=RequestMethod.POST)
    public ResponseEntity<PollDTO> createPoll(@RequestBody Poll poll, @PathVariable Long chatId) {
        poll.setChat(chatService.getChatById(chatId));
        poll.setPollCreator(poll.getChat().getAdmin());
        poll = pollService.create(poll);
        PollDTO dto = pollRepository.findByPollId(poll.getPollId());
        return new ResponseEntity<>( dto, HttpStatus.CREATED);
    }

    @GetMapping("/poll/{pollId}")
    public ResponseEntity<HashMap<String, Integer>> countPollVotes(@PathVariable Long pollId) {
        return new ResponseEntity<>(pollService.getPollVotes(pollId), HttpStatus.OK);
    }

    @RequestMapping(value="/chat/{id}/polls", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll,@PathVariable Long id, @PathVariable Long pollId) {
        return new ResponseEntity<>(pollService.update(pollId,poll), HttpStatus.CREATED);
    }
    @RequestMapping(value="/chat/{id}/polls", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll( @PathVariable Long id) {
        try {
            verifyPollById(id);
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
