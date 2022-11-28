package com.example.virtual_life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtual_life.service.FriendService;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

    @PutMapping("/friend")
    public int addNewFriend(@RequestBody ObjectNode requestBody) {
        if(requestBody.get("userId") == null
            || requestBody.get("friendId") == null) {
                return 0;
        }
        Long userId = requestBody.get("userId").asLong();
        Long friendId = requestBody.get("friendId").asLong();
        return friendService.addNewFriend(userId, friendId);
    }
    
    @DeleteMapping("/{userId}/friend/{friendId}")
    public int deleteFriend(@PathVariable Long userId,
                            @PathVariable Long friendId) {
        return friendService.deleteFriend(userId, friendId);
    }
}
