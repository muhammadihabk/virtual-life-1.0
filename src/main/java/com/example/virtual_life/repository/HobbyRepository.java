package com.example.virtual_life.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.virtual_life.entity.Hobby;

@Repository
public class HobbyRepository {
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Hobby> findAllHobbies() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Hobby> criteria = builder.createQuery(Hobby.class);
        Root<Hobby> root = criteria.from(Hobby.class);
        criteria.select(root.get("name"));
        List<Hobby> hobbies = session.createQuery(criteria).getResultList();
        session.close();
        return hobbies;
    }
}
