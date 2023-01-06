package com.example.testpfsentities;

import org.apache.commons.collections4.EnumerationUtils;
import org.apache.commons.lang3.EnumUtils;
import org.h2.command.Command;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableScheduling
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

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
