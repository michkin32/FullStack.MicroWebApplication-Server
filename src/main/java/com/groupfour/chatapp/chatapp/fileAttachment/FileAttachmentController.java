package com.groupfour.chatapp.chatapp.fileAttachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileAttachmentController {

    private FileAttachmentService fileAttachmentService;

    @Autowired
    public FileAttachmentController(FileAttachmentService fileAttachmentService) {
        this.fileAttachmentService = fileAttachmentService;
    }
}
