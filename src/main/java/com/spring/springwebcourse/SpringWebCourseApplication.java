package com.spring.springwebcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.spring.Entities")
@EnableJpaRepositories(basePackages = "com.spring.repositories")
@ComponentScan(basePackages = {"com.spring"})
public class SpringWebCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebCourseApplication.class, args);
    }

}
