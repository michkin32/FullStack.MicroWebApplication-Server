package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.repositories.FileAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileAttachmentService {

    private FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    public FileAttachmentService(FileAttachmentRepository fileAttachmentRepository) {
        this.fileAttachmentRepository = fileAttachmentRepository;
    }
}
