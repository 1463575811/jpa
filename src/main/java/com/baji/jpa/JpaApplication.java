package com.baji.jpa;

import com.baji.jpa.entity.User;
import com.baji.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class JpaApplication implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    public void init(){
        User baji = User.builder().name("baji").build();
        userRepository.save(baji);
        Iterable<User> all = userRepository.findAll();
        all.forEach(user -> log.info(user.toString()));
    }

}
