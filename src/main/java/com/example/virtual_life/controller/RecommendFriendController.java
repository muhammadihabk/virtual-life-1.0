package com.example.virtual_life.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtual_life.service.RecommendFriendService;

@RestController
@RequestMapping("/app/recommendfriend")
public class RecommendFriendController {
    private RecommendFriendService recommendFriendService;
    
    @Autowired
    public RecommendFriendController(RecommendFriendService recommendFriendService) {
        this.recommendFriendService = recommendFriendService;
    }
    
    @GetMapping("/2ndlevelfriend/{userId}")
    public List<Object[]> secondLvlFriendsOfUser(@PathVariable Long userId) {
        return recommendFriendService.secondLvlFriendsOfUser(userId);
    }

    @GetMapping("/nonfriends-with-common-hobbies/{userId}")
    public List<Object[]> nonfriendsWithCommonHobbies(@PathVariable Long userId) {
        return recommendFriendService.nonfriendsWithCommonHobbies(userId);
    }
}
