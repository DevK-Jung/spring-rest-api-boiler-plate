package com.kjung.boilerplate.file;

import org.springframework.web.multipart.MultipartFile;

public interface StorageHelper {
    String uploadFile(MultipartFile file, String filePath, String fileName);

    byte[] getFileBytes(String filePath, String fileName);

    void deleteFile(String filePath, String fileName);
}
