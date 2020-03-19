package com.yun.util.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableCaching
@EntityScan(basePackages = {"com.yun.util.examples"})
public class YunUtilExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunUtilExamplesApplication.class, args);
    }
}
