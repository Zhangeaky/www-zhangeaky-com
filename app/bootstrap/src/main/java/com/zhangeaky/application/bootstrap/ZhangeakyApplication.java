package com.zhangeaky.application.bootstrap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ZhangeakyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ZhangeakyApplication.class, args);

    }
}
