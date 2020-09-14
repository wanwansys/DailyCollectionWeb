package com.shkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@MapperScan("com.shkj.mapper") //扫描的mapper
@SpringBootApplication
@EnableCaching //开启缓存
public class DailycollectwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailycollectwebApplication.class, args);
    }


}