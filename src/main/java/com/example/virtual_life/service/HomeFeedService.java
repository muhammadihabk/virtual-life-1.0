package com.example.virtual_life.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.virtual_life.repository.HomeFeedRepository;

@Service
public class HomeFeedService {
    private HomeFeedRepository homeFeedRepository;

    @Autowired
    public HomeFeedService(HomeFeedRepository homeFeedRepository) {
        this.homeFeedRepository = homeFeedRepository;
    }

    public List<Object[]> findAll(Long userId) {
        return homeFeedRepository.findAll(userId);
    }
}
