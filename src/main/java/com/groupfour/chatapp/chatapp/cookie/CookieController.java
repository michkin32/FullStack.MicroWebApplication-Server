package com.groupfour.chatapp.chatapp.cookie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cookies")
public class CookieController {

    @GetMapping
    public List<Cookie> getCookies() {
        return Arrays.asList(new Cookie("flavour","Chocolate"), new Cookie("flavour","vanilla"),
                new Cookie("flavour","cinnamon"), new Cookie("flavour","coconut"));
    }
}
