package com.groupfour.chatapp.chatapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileAttachment {

    @Id
    @GeneratedValue
    private Long fileId;

    private String path;
}
