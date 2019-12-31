package com.groupfour.chatapp.chatapp.controllers;


import com.groupfour.chatapp.chatapp.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/polls")
@CrossOrigin(origins = "http://localhost:4200")
public class OptionController {

    private OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }


}
