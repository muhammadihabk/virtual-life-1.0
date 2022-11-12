package com.example.virtual_life.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.virtual_life.entity.User;

@Repository
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public User save(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }
    
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
        query.from(User.class);
        List<User> users = session.createQuery(query).getResultList();
        session.close();
        return users;
    }
    
    public Optional<User> findById(Long id) {
        return null;
    }

    public void deleteById(Long id) {

    }

    public Optional<User> findByEmail(String email) {
        return null;
    }
}
