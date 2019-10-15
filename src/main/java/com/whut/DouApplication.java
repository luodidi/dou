package com.whut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whut.mapper")
public class DouApplication {

    public static void main(String[] args) {
        SpringApplication.run(DouApplication.class, args);
    }

}
