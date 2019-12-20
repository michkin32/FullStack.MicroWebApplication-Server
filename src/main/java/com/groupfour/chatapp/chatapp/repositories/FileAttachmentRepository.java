package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.models.FileAttachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAttachmentRepository extends CrudRepository<FileAttachment, Long> {
}
