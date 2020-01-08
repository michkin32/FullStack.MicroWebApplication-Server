package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.dataprojections.FriendRequestDTO;
import com.groupfour.chatapp.chatapp.models.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {
//    Iterable<FriendRequest> createFriendRequest(String name);
//    FriendRequest sendFriendRequest(FriendRequest friendRequest);
    FriendRequestDTO findByFriendRequestId(Long id);
}
