package com.groupfour.chatapp.chatapp.services.userDetails;


import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //to do implement access to repository to check for username and password
    //check login controller request  with user repository
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try{
             com.groupfour.chatapp.chatapp.models.User user = userRepository.findByUserName(userName);
            return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException("Username or password does not exist", e);
        }
    }
}