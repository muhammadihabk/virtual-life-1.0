package com.example.virtual_life.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        Optional<User> optionalUser = Optional.ofNullable(session.get(User.class, id));
        session.close();
        return optionalUser;
    }
    
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        final String sqlScript = "DELETE"
                                + " FROM virtual_life_user"
                                + " WHERE id = :id";
        session.createNativeQuery(sqlScript).setParameter("id", id).executeUpdate();
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
