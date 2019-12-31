package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.repositories.QuestionRepository;
import com.groupfour.chatapp.chatapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost4200")
public class QuestionController {

    private QuestionService questionService;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
}
