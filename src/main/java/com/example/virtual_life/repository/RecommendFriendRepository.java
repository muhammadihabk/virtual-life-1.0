package com.example.virtual_life.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendFriendRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Object[]> secondLvlFriendsOfUser(Long userId) {
        String sqlScript = """
            SELECT DISTINCT(id), first_name, last_name
            FROM virtual_life_user u
            INNER JOIN(
                SELECT friend_id
                FROM(
                    SELECT u.id
                    FROM virtual_life_user u
                    INNER JOIN(
                        SELECT f.user_id, f.friend_id
                        FROM friendship f
                        UNION ALL
                        SELECT f.friend_id, f.user_id
                        FROM friendship f
                        ) all_friends
                        ON u.id = all_friends.friend_id
                        AND all_friends.user_id = :userId
                    ) user_friends
                INNER JOIN (
                    SELECT f.user_id, f.friend_id
                    FROM friendship f
                    UNION ALL
                    SELECT f.friend_id, f.user_id
                    FROM friendship f
                ) friendship
                    ON user_friends.id = friendship.user_id
            ) friends_friends
                ON u.id = friends_friends.friend_id;
                """;
        Session session = sessionFactory.openSession();
        List<Object[]> names = session.createNativeQuery(sqlScript)
                                    .setParameter("userId", userId)
                                    .getResultList();
        session.close();
        return names;
    }

    public List<Object[]> nonfriendsWithCommonHobbies(Long userId) {
        String sqlScript = """
            SELECT u.first_name, u.last_name, h.hobby_name
            FROM virtual_life_user u
            INNER JOIN(
                SELECT *
                FROM user_hobby
                WHERE user_id NOT IN(
                    SELECT friendship.friend_id
                    FROM(
                        SELECT f.user_id, f.friend_id
                        FROM friendship f
                        UNION ALL
                        SELECT f.friend_id, f.user_id
                        FROM friendship f
                    ) friendship
                    WHERE friendship.user_id = :userId 
                ) AND user_id != :userId 
                    AND hobby_id IN (
                        SELECT hobby_id
                        FROM user_hobby
                        WHERE user_id = :userId 
                    )
            ) nonfriends_with_common_hobbies
            ON u.id = nonfriends_with_common_hobbies.user_id
            INNER JOIN hobby h
            ON h.id = nonfriends_with_common_hobbies.hobby_id;
                """;
        Session session = sessionFactory.openSession();
        List<Object[]> rows = session.createNativeQuery(sqlScript)
                                    .setParameter("userId", userId)
                                    .getResultList();
        session.close();
        return rows;
    }
}
