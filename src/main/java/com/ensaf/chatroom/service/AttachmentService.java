package com.ensaf.chatroom.service;

import com.ensaf.chatroom.config.StorageProperties;
import com.ensaf.chatroom.dao.AttachmentRepository;
import com.ensaf.chatroom.dto.AttachmentDto;
import com.ensaf.chatroom.entity.Attachment;
import com.ensaf.chatroom.exception.FileNotFoundException;
import com.ensaf.chatroom.exception.StorageException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Service for handling document storage and retrieval.
 */
@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final StorageProperties storageProperties;
    private final AttachmentRepository attachmentRepository;

    private Path rootLocation;

    /**
     * Initializes the storage location based on the configured properties.
     *
     * @throws StorageException if the storage location is invalid or cannot be created.
     */
    @PostConstruct
    public void init() {
        if (!StringUtils.hasText(storageProperties.getLocation())) {
            throw new StorageException("File upload location can not be Empty.");
        }
        try {
            this.rootLocation = Paths.get(storageProperties.getLocation());
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException(e.getMessage(), e);
        }
    }

    /**
     * Stores a file in the configured storage location.
     *
     * @param file The file to store.
     * @return The ID of the stored document.
     * @throws StorageException if the file is empty or cannot be stored.
     */
    public String store(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }
        try {
            Attachment attachment = attachmentRepository.save(Attachment.builder()
                    .filename(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .build());

            Path destinationFile = this.rootLocation.resolve(attachment.getId()).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            file.transferTo(destinationFile);
            return attachment.getId();
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    /**
     * Loads a file as a resource.
     *
     * @param id The ID of the document to load.
     * @return A {@link AttachmentDto} containing the file's metadata and resource.
     * @throws FileNotFoundException if the file does not exist or is not readable.
     */
    public AttachmentDto loadAsResource(String id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(FileNotFoundException::new);
        try {
            Path file = load(id);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new FileNotFoundException("Could not read file: " + id);
            }
            return AttachmentDto.builder()
                    .filename(attachment.getFilename())
                    .size(attachment.getSize())
                    .contentType(attachment.getContentType())
                    .resource(resource)
                    .build();
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + id, e);
        }
    }

    public void delete(String id) {
        //TODO il ne faut pas oublier de supprimer le document
        attachmentRepository.deleteById(id);
    }
}