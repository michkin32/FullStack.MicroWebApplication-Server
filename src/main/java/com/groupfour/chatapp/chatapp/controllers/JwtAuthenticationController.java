package com.groupfour.chatapp.chatapp.controllers;

import java.util.Objects;

import com.groupfour.chatapp.chatapp.models.authentication.JwtRequest;
import com.groupfour.chatapp.chatapp.models.authentication.JwtResponse;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import com.groupfour.chatapp.chatapp.services.userDetails.UserDetailsServiceImpl;
import com.groupfour.chatapp.chatapp.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtility jwtTokenUtility;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/loginq", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {


        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService

                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtility.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }

}