package com.groupfour.chatapp.chatapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping(value = "/")
    public String showLoginPage(ModelMap model) {
        model.put ("name", "OUR_WEBSITE_NAME_GOES_HERE");
        return "Welcome";
    }




}
