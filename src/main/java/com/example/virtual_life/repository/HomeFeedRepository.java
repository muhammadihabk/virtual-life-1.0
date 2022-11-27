package com.example.virtual_life.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeFeedRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Object[]> findAll(Long userId) {
        String sqlScript = """
                SELECT first_name, last_name, post_text, date_posted
                FROM post
                INNER JOIN virtual_life_user u
                    ON post.author = u.id
                WHERE author IN(
                    SELECT friend_id
                    FROM (
                        SELECT f.user_id, f.friend_id
                        FROM friendship f
                        UNION ALL
                        SELECT f.friend_id, f.user_id
                        FROM friendship f
                    ) all_friends
                    WHERE user_id = :userId
                );
                """;
        Session session = sessionFactory.openSession();
        List<Object[]> rows = session.createNativeQuery(sqlScript)
                                    .setParameter("userId", userId)
                                    .getResultList();
        session.close();
        return rows;
    }
}
