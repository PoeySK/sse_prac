package com.sehwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SsePracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsePracApplication.class, args);
    }

}
