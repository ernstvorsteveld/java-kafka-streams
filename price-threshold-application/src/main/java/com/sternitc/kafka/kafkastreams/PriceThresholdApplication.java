package com.sternitc.kafka.kafkastreams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@ComponentScan(basePackages = "com.sternitc.pricethreshold.api")
public class PriceThresholdApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceThresholdApplication.class, args);
    }

    @Autowired
    private ApplicationContext appContext;

//    @Override
//    public void run(String... args) throws Exception {
//        String[] beans = appContext.getBeanDefinitionNames();
//
//        Arrays.sort(beans);
//
//        for (String bean : beans) {
//
//            System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
//        }
//    }
}
