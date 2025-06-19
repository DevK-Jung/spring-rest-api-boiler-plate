package com.kjung.boilerplate.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface StorageHelper {
    String uploadFile(MultipartFile file, String filePath, String fileName);

    byte[] getFileBytes(String filePath, String fileName); // 전체 파일 바이트

    InputStream getFileStream(String filePath, String fileName); // 파일 스트림

    void deleteFile(String filePath, String fileName);
}
