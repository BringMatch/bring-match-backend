package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.service.UploadImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class UploadImageServiceImpl implements UploadImageService {
    private final Path root = Paths.get("src\\main\\resources\\uploads");

    @Override
    public String getUrlImage(MultipartFile file) throws IOException {
        try {

            Timestamp timestamps = new Timestamp(new Date().getTime());
            String unique_number = String.valueOf(timestamps.getTime());

            String fileName = unique_number + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

    }

    public Resource load(String filename) {
        try {

            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
