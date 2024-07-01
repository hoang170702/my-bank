package com.bank.mybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MybankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybankApplication.class, args);
    }

}
