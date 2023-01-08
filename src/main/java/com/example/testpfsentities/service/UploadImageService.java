package com.example.testpfsentities.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UploadImageService {
    public String getUrlImage(MultipartFile multipartFile) throws IOException;
}
