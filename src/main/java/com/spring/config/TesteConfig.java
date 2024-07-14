package com.spring.config;

import com.spring.Entities.User;
import com.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;


@Configuration
@Profile("teste")
public class TesteConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User(1, "brenno", "brenno@gmail.com", "Sem informacao", "123");
        User user2 = new User(2, "brenno", "brenno2@gmail.com", "Sem informacao", "123");

        this.userRepository.saveAll(Arrays.asList(user, user2));
    }
}
