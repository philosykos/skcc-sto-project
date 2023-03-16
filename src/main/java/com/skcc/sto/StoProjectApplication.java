package com.skcc.sto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
//@EnableJpaAuditing
@SpringBootApplication
public class StoProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run( StoProjectApplication.class, args );
    }
}
