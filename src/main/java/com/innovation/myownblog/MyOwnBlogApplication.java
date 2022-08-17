package com.innovation.myownblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyOwnBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOwnBlogApplication.class, args);
    }

}
