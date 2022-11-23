package com.example.virtual_life.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.virtual_life.repository.FriendRepository;

@Service
public class FriendService {
    private FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Object[]> findAllFriendsOfUser(Long userId) {
        return friendRepository.findAllFriendsOfUser(userId);
    }

    public int addNewFriend(Long userId, Long friendId) {
        return friendRepository.addNewFriend(userId, friendId);
    }
    
    public int deleteFriend(Long userId, Long friendId) {
        return friendRepository.deleteFriend(userId, friendId);
    }

}
