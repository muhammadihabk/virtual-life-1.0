package com.example.virtual_life.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.virtual_life.entity.User;
import com.example.virtual_life.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()) {
            throw new IllegalStateException("email is taken");
        }
        return userRepository.save(user);
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateUser(Long id,
                            String firstName,
                            String lastName,
                            String email,
                            String password) {
        User user = userRepository.findById(id)
            .orElseThrow(() ->
                new IllegalStateException("User with id " + id + " doesn't exist")
            );
        
        if(firstName != null
            && firstName.length() > 0
            && !firstName.equals(user.getFirstName())) {
            user.setFirstName(firstName);
        }
        if(lastName != null
            && lastName.length() > 0
            && !lastName.equals(user.getLastName())) {
            user.setLastName(lastName);
        }
        if(email != null
            && email.length() > 0
            && !email.equals(user.getEmail())) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if(userOptional.isPresent()) {
                throw new IllegalStateException("Email is taken");
            }
            user.setEmail(email);
        }
        if(password != null
            && password.length() > 0
            && !password.equals(user.getPassword())) {
                user.setPassword(password);
            }
    }
    
    public void deleteUser(Long id) {
        // boolean exists = userRepository.existsById(id);
        // if(!exists) {
        //     throw new IllegalStateException("User with id " + id + " doesn't exist");
        // }
        userRepository.deleteById(id);
    }
}
