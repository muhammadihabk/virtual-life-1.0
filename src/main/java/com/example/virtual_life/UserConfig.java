package com.example.virtual_life;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.virtual_life.repository.UserRepository;
import com.example.virtual_life.entity.User;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user1 = new User("Muhammad", "Ihab", "m.ihab@mail", "password", new Date());
            User user2 = new User("Ihab", "Anass", "i.anass@mail", "password", new Date());
            User user3 = new User("Hazem", "Moustafa", "h.moustafa@mail", "password", new Date());
            userRepository.saveAll(
                List.of(user1, user2, user3)
            );
        };
    }
}
