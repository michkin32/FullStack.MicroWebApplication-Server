package com.groupfour.chatapp.chatapp.controllers;

import com.groupfour.chatapp.chatapp.services.FileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD

=======
@CrossOrigin
>>>>>>> dev
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileAttachmentController {

    private FileAttachmentService fileAttachmentService;

    @Autowired
    public FileAttachmentController(FileAttachmentService fileAttachmentService) {
        this.fileAttachmentService = fileAttachmentService;
    }
}
