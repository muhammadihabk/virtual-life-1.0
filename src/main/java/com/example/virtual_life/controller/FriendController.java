package com.example.virtual_life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtual_life.service.FriendService;

@RestController
@RequestMapping("/app/user")
public class FriendController {
    FriendService friendService;
    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }
    
    @GetMapping("/{userId}/friend")
    public List<Object[]> findAllFriendsOfUser(@PathVariable Long userId) {
        return friendService.findAllFriendsOfUser(userId);
    }

    @PutMapping("/{userId}/friend/{friendId}")
    public int addNewFriend(@PathVariable Long userId,
                            @PathVariable Long friendId) {
        return friendService.addNewFriend(userId, friendId);
    }
}
