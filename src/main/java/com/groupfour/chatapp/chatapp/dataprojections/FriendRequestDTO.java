package com.groupfour.chatapp.chatapp.dataprojections;

import com.groupfour.chatapp.chatapp.models.User;

public interface FriendRequestDTO {
    UserDTO getRecipient();
    Long getFriendRequestId();
    UserDTO getSender();
}
