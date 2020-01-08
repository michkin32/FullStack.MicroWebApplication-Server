package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.models.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository {
    Iterable<FriendRequest> createFriendRequest(String name);
    Iterable<FriendRequest> sendFriendRequest(FriendRequest friendRequest);
}
