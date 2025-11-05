package com.personal_blog.my_personal_blog.storage;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3StorageService {
    @Autowired
    private S3Template s3Template;

    @Value("${s3.bucket.name}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            S3Resource resource = s3Template.upload(bucketName, fileName, file.getInputStream());

            return resource.getURL().toString();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao fazer upload do arquivo",e);
        }
    }
}
