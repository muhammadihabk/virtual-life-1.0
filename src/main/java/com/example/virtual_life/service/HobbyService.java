package com.example.virtual_life.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.virtual_life.entity.Hobby;
import com.example.virtual_life.repository.HobbyRepository;

@Service
public class HobbyService {
    @Autowired
    private HobbyRepository hobbyRepository;

    public List<Hobby> findAllHobbies() {
        return hobbyRepository.findAllHobbies();
    }
}
