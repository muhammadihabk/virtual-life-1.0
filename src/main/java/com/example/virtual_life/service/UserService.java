package com.example.virtual_life.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public List<Object[]> findAll() {
        return userRepository.findAll();
    }
    
    public List<Object[]> findById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(Long id,
                            String firstName,
                            String lastName,
                            String email,
                            String password) {
        userRepository.updateUser(id, firstName, lastName, email, password);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
