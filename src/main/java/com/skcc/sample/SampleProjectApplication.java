package com.skcc.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
//@EnableJpaAuditing
@SpringBootApplication
public class SampleProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run( SampleProjectApplication.class, args );
    }
}
