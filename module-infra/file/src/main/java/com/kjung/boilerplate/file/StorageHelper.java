package com.kjung.boilerplate.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 파일 저장소 헬퍼 인터페이스.
 * <p>
 * 로컬 디스크, 클라우드 스토리지 등 다양한 저장소에 대한 파일 업로드, 다운로드, 삭제 등의 기능을 정의한다.
 * </p>
 *
 * @author 김정현
 */
public interface StorageHelper {
    /**
     * 파일을 지정한 경로에 업로드한다.
     *
     * @param file     업로드할 MultipartFile
     * @param filePath 저장할 디렉토리 경로
     * @param fileName 저장할 파일명
     * @return 업로드된 파일의 전체 경로
     */
    String uploadFile(MultipartFile file, String filePath, String fileName);

    /**
     * 지정된 파일을 바이트 배열로 반환한다.
     *
     * @param filePath 파일이 저장된 경로
     * @param fileName 파일명
     * @return 파일의 바이트 배열
     */
    byte[] getFileBytes(String filePath, String fileName); // 전체 파일 바이트

    /**
     * 지정된 파일에 대한 InputStream을 반환한다.
     *
     * @param filePath 파일이 저장된 경로
     * @param fileName 파일명
     * @return 파일의 InputStream
     */
    InputStream getFileStream(String filePath, String fileName); // 파일 스트림

    /**
     * 지정된 파일을 삭제한다.
     *
     * @param filePath 파일이 저장된 경로
     * @param fileName 삭제할 파일명
     */
    void deleteFile(String filePath, String fileName);
}
