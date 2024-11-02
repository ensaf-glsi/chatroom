package com.ensaf.chatroom.dao;

import com.ensaf.chatroom.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}