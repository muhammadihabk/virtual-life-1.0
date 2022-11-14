package com.example.virtual_life.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        session.close();
        return user;
    }
    
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        String queryScript = "FROM User";
        Query query = session.createQuery(queryScript);
        List users = query.list();
        session.close();
        return users;
    }
    
    public Optional<User> findById(Long id) {
        Session session = sessionFactory.openSession();
        return null;
    }
    
    public void deleteById(Long id) {

    }

    public Optional<User> findByEmail(String email) {
        return null;
    }
}
