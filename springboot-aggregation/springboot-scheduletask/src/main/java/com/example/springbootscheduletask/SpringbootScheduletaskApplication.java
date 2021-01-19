package com.example.springbootscheduletask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "com.example.springbootscheduletask.task")
public class SpringbootScheduletaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduletaskApplication.class, args);
        log.info("testTask2");
    }

}
