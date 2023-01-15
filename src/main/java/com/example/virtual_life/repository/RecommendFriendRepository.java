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
            SELECT u.first_name, u.last_name
            FROM (
                SELECT friend_id friend_lvl_1
                FROM (
                    SELECT f1.user_id, f1.friend_id
                    FROM friendship f1
                    UNION ALL
                        SELECT f2.friend_id, f2.user_id
                        FROM friendship f2
                ) all_friends
                WHERE all_friends.user_id = :userId
            ) friends_lvl_1
            INNER JOIN (
                SELECT f1.user_id, f1.friend_id
                FROM friendship f1
                UNION ALL
                    SELECT f2.friend_id, f2.user_id
                    FROM friendship f2
            ) all_friends
            ON friends_lvl_1.friend_lvl_1 = all_friends.user_id
            INNER JOIN virtual_life_user u
            ON u.id = all_friends.friend_id
            WHERE all_friends.friend_id != :userId
                AND all_friends.friend_id
                    NOT IN (
                            SELECT all_friends.friend_id
                            FROM virtual_life_user u
                            INNER JOIN (
                                    SELECT f1.user_id, f1.friend_id
                                    FROM friendship f1
                                    UNION ALL
                                        SELECT f2.friend_id, f2.user_id
                                        FROM friendship f2
                            ) all_friends
                            ON all_friends.user_id = :userId
                        )
            GROUP BY all_friends.friend_id, u.first_name, u.last_name;
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
