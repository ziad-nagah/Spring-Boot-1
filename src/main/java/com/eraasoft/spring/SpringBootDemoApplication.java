package com.eraasoft.spring;

import com.eraasoft.spring.model.EraaSoftSchool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class SpringBootDemoApplication {

    public static void main(String[] args) {
      SpringApplication.run(SpringBootDemoApplication.class, args);


    }

}
