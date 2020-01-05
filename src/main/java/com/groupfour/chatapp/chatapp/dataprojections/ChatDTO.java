package com.groupfour.chatapp.chatapp.dataprojections;

import com.groupfour.chatapp.chatapp.models.Chat;
import com.groupfour.chatapp.chatapp.models.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChatDTO {
    private Long chatId;

    private String chatName;
    private Date timeStamp;
    private Long adminId;
    private Set<Long> userIds;

    public ChatDTO(Long chatId, String chatName, Date timeStamp, User admin, Set<User> users) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.timeStamp = timeStamp;

        if(admin != null) {
            this.adminId = admin.getUserId();
        }
        userIds = new HashSet<Long>();
        for (User user : users) {
            userIds.add(user.getUserId());
        }
    }

    public ChatDTO(Chat chat) {
        this.chatId = chat.getChatId();
        this.chatName = chat.getChatName();
        this.timeStamp = chat.getTimeStamp();

        if(chat.getAdmin() != null) {
            this.adminId = chat.getAdmin().getUserId();
        }
        userIds = new HashSet<Long>();
        Set<User> users = chat.getUsers();
        for (User user : users) {
            userIds.add(user.getUserId());
        }
    }

    public Long getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Long getAdminID() {
        return adminId;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }
}
