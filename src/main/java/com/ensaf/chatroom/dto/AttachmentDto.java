package com.ensaf.chatroom.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.core.io.Resource;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class AttachmentDto {
    private String filename;
    private String contentType;
    private Long size;
    private Resource resource;
}