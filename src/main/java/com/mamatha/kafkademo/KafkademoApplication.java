package com.mamatha.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import javax.servlet.Filter;

@SpringBootApplication
public class KafkademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkademoApplication.class, args);
    }

    //Support cache
    @Bean
    public Filter filter() {
        ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
        return filter;
    }
}
