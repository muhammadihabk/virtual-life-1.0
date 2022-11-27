package com.example.virtual_life.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtual_life.service.HomeFeedService;

@RestController
@RequestMapping("/app/home")
public class HomeFeedController {
    private HomeFeedService homeFeedService;
    
    @Autowired
    public HomeFeedController(HomeFeedService homeFeedService) {
        this.homeFeedService = homeFeedService;
    }
    
    @GetMapping("/{userId}")
    public List<Object[]> findAll(@PathVariable Long userId) {
        return homeFeedService.findAll(userId);
    }
}
