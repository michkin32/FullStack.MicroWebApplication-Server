package com.groupfour.chatapp.chatapp.repositories;


import com.groupfour.chatapp.chatapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



//    @Repository
//    public interface UserInfoRepository extends JpaRepository<User,Integer> {

        Boolean existsByUsername(String username);
        User findByUsername(String username);

}
