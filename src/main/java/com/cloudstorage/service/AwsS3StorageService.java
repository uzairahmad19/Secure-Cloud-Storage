package com.cloudstorage.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cloudstorage.model.FileMetadata;
import com.cloudstorage.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AwsS3StorageService implements StorageService {

    private final AmazonS3 s3client;
    private final FileMetadataRepository metadataRepository;
    private final String bucketName;

    public AwsS3StorageService(AmazonS3 s3client,
                               FileMetadataRepository metadataRepository,
                               @Value("${aws.s3.bucketName}") String bucketName) {
        this.s3client = s3client;
        this.metadataRepository = metadataRepository;
        this.bucketName = bucketName;
    }

    @Override
    public String store(MultipartFile file) throws Exception {
        // Convert the MultipartFile to a temporary file
        File convertedFile = convertMultiPartToFile(file);

        // Generate a unique file name to avoid collisions in S3
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = generateUniqueFileName(originalFileName);

        try {
            // Upload file to S3
            s3client.putObject(new PutObjectRequest(bucketName, uniqueFileName, convertedFile));
            String fileUrl = s3client.getUrl(bucketName, uniqueFileName).toString();

            // Save metadata into the database
            FileMetadata metadata = new FileMetadata(
                    uniqueFileName,
                    fileUrl,
                    file.getSize(),
                    file.getContentType(),
                    LocalDateTime.now()
            );
            metadataRepository.save(metadata);

            return fileUrl;
        } catch(Exception e) {
            throw new Exception("File upload failed: " + e.getMessage(), e);
        } finally {
            // Delete the temporary file regardless of the outcome
            if (convertedFile.exists()) {
                convertedFile.delete();
            }
        }
    }

    @Override
    public void delete(String fileName) {
        // Delete file from S3
        s3client.deleteObject(bucketName, fileName);
        // Delete metadata from the repository
        metadataRepository.deleteByFileName(fileName);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        // Create a unique temporary file using createTempFile to avoid collisions
        File tempFile = File.createTempFile("upload-", "-" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }

    private String generateUniqueFileName(String originalFileName) {
        // Use UUID to generate a unique identifier and append it to the original filename
        String uuid = UUID.randomUUID().toString();
        return uuid + "_" + originalFileName;
    }
}