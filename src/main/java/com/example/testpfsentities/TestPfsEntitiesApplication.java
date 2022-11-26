package com.example.testpfsentities;

import org.apache.commons.collections4.EnumerationUtils;
import org.apache.commons.lang3.EnumUtils;
import org.h2.command.Command;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestPfsEntitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestPfsEntitiesApplication.class, args);
    }

    enum Test {
        HAHA, YOY
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            System.out.println(EnumUtils.isValidEnum(Test.class, Test.HAHA.name()) + "haha");
        };

    }

}
