package com.example.virtual_life.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.virtual_life.entity.Friendship;
import com.example.virtual_life.entity.User;

@Repository
public class FriendRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Object[]> findAllFriendsOfUser(Long userId) {
        Session session = sessionFactory.openSession();
        String queryString = """
                                SELECT first_name, last_name
                                FROM virtual_life_user u
                                INNER JOIN (
                                    SELECT f.user_id, f.friend_id
                                    FROM virtual_life_user u
                                    INNER JOIN friendship f
                                        ON u.id = f.friend_id
                                    UNION ALL
                                    SELECT f.friend_id, f.user_id
                                    FROM virtual_life_user u
                                    INNER JOIN friendship f
                                        ON u.id = f.friend_id
                                    ) all_friends_of_user
                                    ON u.id = all_friends_of_user.friend_id
                                WHERE all_friends_of_user.user_id = :userId ;
                            """;
        List<Object[]> rows = session.createNativeQuery(queryString)
                                .setParameter("userId", userId)
                                .getResultList();
        session.close();
        return rows;
    }
}
