package com.cloudstorage.controller;

import com.cloudstorage.model.FileMetadata;
import com.cloudstorage.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files/metadata")
public class FileMetadataController {

    private final FileMetadataRepository metadataRepository;

    @Autowired
    public FileMetadataController(FileMetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    @GetMapping
    public ResponseEntity<List<FileMetadata>> getAllMetadata() {
        return ResponseEntity.ok(metadataRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getMetadataById(@PathVariable Long id) {
        return metadataRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMetadata(@PathVariable Long id) {
        FileMetadata metadata = metadataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metadata not found"));

        metadataRepository.delete(metadata);
        return ResponseEntity.ok("File metadata deleted successfully: " + id);
    }
}
