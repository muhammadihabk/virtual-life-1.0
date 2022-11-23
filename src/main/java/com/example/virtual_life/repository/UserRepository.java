package com.example.virtual_life.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }
    
    public List<Object[]> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<User> root = criteria.from(User.class);
        criteria.select(builder.array(
                            root.get("firstName"),
                            root.get("lastName"),
                            root.get("email"),
                            root.get("dob")));
        List<Object[]> users = session.createQuery(criteria).getResultList();
        session.close();
        return users;
    }

    public List<Object[]> findById(Long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<User> root = criteria.from(User.class);
        criteria.select(builder.array(
                            root.get("firstName"),
                            root.get("lastName"),
                            root.get("email"),
                            root.get("dob")));
        criteria.where(builder.equal(root.get("id"), id));
        List<Object[]> user = session.createQuery(criteria).getResultList();
        session.close();
        return user;
    }
    
    public Optional<User> findByIdAllData(Long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("id"), id));
        Optional<User> optionalUser = session.createQuery(criteria).uniqueResultOptional();
        session.close();
        return optionalUser;
    }
    
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<User> criteria = builder.createCriteriaDelete(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("id"), id));
        session.createQuery(criteria).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public Optional<User> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("email"), email));
        Optional<User> user = session.createQuery(criteria).uniqueResultOptional();
        session.close();
        return user;
    }
}
