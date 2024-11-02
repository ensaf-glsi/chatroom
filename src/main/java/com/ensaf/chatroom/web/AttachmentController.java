package com.ensaf.chatroom.web;

import com.ensaf.chatroom.dto.AttachmentDto;
import com.ensaf.chatroom.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/attachments")
@RequiredArgsConstructor
public class AttachmentController {

	private final AttachmentService attachmentService;

    @GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> download(@PathVariable String filename) {
        AttachmentDto documentDto = attachmentService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentDto.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, documentDto.getContentType())
                .header(HttpHeaders.CONTENT_LENGTH, documentDto.getSize().toString())
                .body(documentDto.getResource());
	}

	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file) {
        String filename = attachmentService.store(file);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/attachments/")
                .path(filename)
                .toUriString();

    }

    @DeleteMapping("/{filename:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String filename) {
        attachmentService.delete(filename);
    }

}