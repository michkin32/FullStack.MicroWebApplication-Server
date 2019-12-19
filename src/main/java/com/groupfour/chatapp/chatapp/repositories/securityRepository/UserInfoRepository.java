package com.groupfour.chatapp.chatapp.repositories.securityRepository;


import com.groupfour.chatapp.chatapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<User,Integer> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);


}
