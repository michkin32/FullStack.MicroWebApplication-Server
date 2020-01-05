package com.groupfour.chatapp.chatapp.dataprojections;

import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public interface ChatDTO {
    Long getChatId();
    String getChatName();
    Date getTimeStamp();
}
