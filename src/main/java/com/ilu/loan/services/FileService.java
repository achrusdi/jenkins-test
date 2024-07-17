package com.ilu.loan.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ilu.loan.dto.response.FileResponse;

public interface FileService {
    FileResponse storeFile(MultipartFile file, String path, String id) throws IOException;

    public boolean isFileExists(String path, String fileName);

    public boolean deleteFile(String fileName);

    public byte[] downloadFile(String fileName) throws IOException;

    public byte[] showFile(String fileName) throws IOException;
}