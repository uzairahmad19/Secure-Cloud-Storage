package com.cloudstorage.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file) throws Exception;
    void delete(String fileName) throws Exception;
}