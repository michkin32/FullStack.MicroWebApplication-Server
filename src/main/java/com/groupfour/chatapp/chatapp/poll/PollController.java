package com.groupfour.chatapp.chatapp.poll;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollController {

    private PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }
}
