package com.example.virtual_life.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.virtual_life.repository.RecommendFriendRepository;

@Service
public class RecommendFriendService {
    private RecommendFriendRepository recommendFriendRepository;

    @Autowired
    public RecommendFriendService(RecommendFriendRepository recommendFriendRepository) {
        this.recommendFriendRepository = recommendFriendRepository;
    }

    public List<Object[]> secondLvlFriendsOfUser(Long userId) {
        return recommendFriendRepository.secondLvlFriendsOfUser(userId);
    }
}
