package com.molin.project200908;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Transactional
public class Project200908Application {

    public static void main(String[] args) {
        SpringApplication.run(Project200908Application.class, args);
    }

}
