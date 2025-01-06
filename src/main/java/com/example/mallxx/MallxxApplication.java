package com.example.mallxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mallxx.mapper")
@SpringBootApplication
public class MallxxApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallxxApplication.class, args);
    }
}
