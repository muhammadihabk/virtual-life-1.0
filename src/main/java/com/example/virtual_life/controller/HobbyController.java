package com.example.virtual_life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtual_life.entity.Hobby;
import com.example.virtual_life.service.HobbyService;

@RestController
@RequestMapping("/app/hobby")
public class HobbyController {
    @Autowired    
    HobbyService hobbyService;

    @GetMapping
    public List<Hobby> findAllHobbies() {
        return hobbyService.findAllHobbies();
    }
}
