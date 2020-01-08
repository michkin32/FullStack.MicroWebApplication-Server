package com.groupfour.chatapp.chatapp.dataprojections;

import java.util.Date;

public interface MessageDTO {
    Long getMessageId();
    Date getTimeStamp();
    String getMessageBody();
    UserDTO getSender();
    ChatDTO getDestinationChat();

}
