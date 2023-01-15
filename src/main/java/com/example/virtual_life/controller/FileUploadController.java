package com.example.virtual_life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.virtual_life.service.FileUploadService;

@RestController
@RequestMapping("/image")
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("imageFile") MultipartFile inputImageFile) {
        return fileUploadService.saveImage(inputImageFile);
    }
}
