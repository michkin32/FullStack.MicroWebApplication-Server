package com.groupfour.chatapp.chatapp.services.securityService;

import java.util.ArrayList;

import com.groupfour.chatapp.chatapp.models.User;
import com.groupfour.chatapp.chatapp.models.securityModel.DAOUser;
import com.groupfour.chatapp.chatapp.models.securityModel.UserDTO;
import com.groupfour.chatapp.chatapp.repositories.securityRepository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());  //TODO need to see what is supposed to be here. https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public User save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        userDao.save(newUser);
        return null;  //TODO no clue why I can't return this.  See bottom example https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
    }
}

