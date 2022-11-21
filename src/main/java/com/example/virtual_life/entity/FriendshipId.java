package com.example.virtual_life.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

import lombok.Data;

@Data
public class FriendshipId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "friend_id")
    private Long friendId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FriendshipId other = (FriendshipId) o;
        return Objects.equals(userId, other.userId) &&
               Objects.equals(friendId, other.friendId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(userId, friendId);
    }
}
