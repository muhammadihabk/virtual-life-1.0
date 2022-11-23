package com.example.virtual_life.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Friendship {
    @EmbeddedId
    private FriendshipId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("friendId")
    private User friend;

    @Column(name = "date_friendship", insertable = false)
    @CreatedDate
    private Date dateFriendship;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Friendship other = (Friendship) o;
        return Objects.equals(user, other.user) &&
               Objects.equals(friend, other.friend);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(user, friend);
    }

    @Override
    public String toString() {
        return "Id: " + "\n\tuserId: " + id.getUserId()
                + "\n\tfriendId: " + id.getFriendId()
                + "\nUser: " + user + "\nFriend: " + friend + "\ndateFriendship: " + dateFriendship;
    }
}