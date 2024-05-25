package com.sternitc.kafka.kafkastreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class PriceThresholdApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceThresholdApplication.class, args);
    }

}
