package com.kjung.boilerplate.file.local;

import com.kjung.boilerplate.file.StorageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@RequiredArgsConstructor
public class LocalStorageHelper implements StorageHelper {

    @Override
    public String uploadFile(MultipartFile file, String filePath, String fileName) {
        Path uploadDir = Paths.get(filePath).toAbsolutePath().normalize();

        try (InputStream inputStream = file.getInputStream()) {

            // 디렉토리 생성
            Files.createDirectories(uploadDir);

            Path targetLocation = uploadDir.resolve(fileName);

            // 동일 파일 존재 시 덮어쓰기
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 업로드된 전체 경로 반환
            return targetLocation.toString();

        } catch (IOException e) {
            log.error(">>> 파일 업로드 에러: {}", e.getMessage(), e);
            throw new RuntimeException("파일 업로드 실패: " + fileName, e);
        }
    }

    @Override
    public byte[] getFileBytes(String filePath, String fileName) {
        Path fileLocation = Paths.get(filePath, fileName).toAbsolutePath();

        try {
            return FileUtils.readFileToByteArray(fileLocation.toFile());
        } catch (IOException e) {
            log.error(">>> 파일 다운로드 에러: {}", e.getMessage(), e);
            throw new RuntimeException("파일 다운로드 실패: " + fileLocation, e);
        }
    }

    @Override
    public InputStream getFileStream(String filePath, String fileName) {
        try {
            return Files.newInputStream(Paths.get(filePath, fileName));
        } catch (IOException e) {
            log.error(">>> 파일 스트림 열기 에러: {}", e.getMessage());
            throw new RuntimeException("파일 스트림 열기 실패", e);
        }
    }

    @Override
    public void deleteFile(String filePath, String fileName) {
        Path fileLocation = Paths.get(filePath, fileName).toAbsolutePath();

        try {
            Files.deleteIfExists(fileLocation);
        } catch (IOException e) {
            log.error(">>> 파일 삭제 에러: {}", e.getMessage(), e);
            throw new RuntimeException("파일 삭제 실패: " + fileLocation, e);
        }
    }
}
