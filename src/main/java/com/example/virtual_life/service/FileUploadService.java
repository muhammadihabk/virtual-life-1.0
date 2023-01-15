package com.example.virtual_life.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    
    public String saveImage(MultipartFile inputImageFile) {
        try {
            String path = "/home/muhammad/muhammad/playground/java/virtual-life/src/main/resources/profile-pictures/";
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = inputImageFile.getOriginalFilename();
            fileName = "<user_id>-<image_id>" + fileName.substring(fileName.indexOf("."));
            File outputImageFile = new File(directory, fileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputImageFile));
            byte[] data = inputImageFile.getBytes();
            bufferedOutputStream.write(data);
            bufferedOutputStream.close();
            return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
