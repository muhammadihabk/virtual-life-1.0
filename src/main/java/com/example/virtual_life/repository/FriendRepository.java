package com.example.virtual_life.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Object[]> findAllFriendsOfUser(Long userId) {
        String queryString = """
                            SELECT first_name, last_name
                            FROM virtual_life_user u
                            INNER JOIN (
                                SELECT f.user_id, f.friend_id
                                FROM friendship f
                                UNION ALL
                                SELECT f.friend_id, f.user_id
                                FROM friendship f
                                ) friendship
                                ON u.id = friendship.friend_id
                            WHERE friendship.user_id = :userId ;
                        """;
        Session session = sessionFactory.openSession();
        List<Object[]> rows = session.createNativeQuery(queryString)
                                        .setParameter("userId", userId)
                                        .getResultList();
        session.close();
        return rows;
    }

    public int addNewFriend(Long userId, Long friendId) {
        String queryString = """
                INSERT INTO Friendship (user_id, friend_id, date_friendship)
                VALUES (:user_id , :friend_id , :date_friendship );
                """;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int affectedRows = session.createNativeQuery(queryString)
                                    .setParameter("user_id", userId)
                                    .setParameter("friend_id", friendId)
                                    .setParameter("date_friendship", new Date())
                                    .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return affectedRows;
    }

    public int deleteFriend(Long userId, Long friendId) {
        String queryString = """
                DELETE FROM Friendship
                WHERE (user_id = :userId OR user_id = :friendId )
                    AND (friend_id = :friendId OR friend_id = :userId ) ;
                """;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int affectedRows = session.createNativeQuery(queryString)
                                    .setParameter("userId", userId)
                                    .setParameter("friendId", friendId)
                                    .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return affectedRows;
    }
}
