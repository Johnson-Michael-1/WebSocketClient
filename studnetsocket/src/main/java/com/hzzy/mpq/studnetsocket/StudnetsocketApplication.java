package com.hzzy.mpq.studnetsocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hzzy.mpq.studnetsocket.mapper")
public class StudnetsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudnetsocketApplication.class, args);
    }
}
