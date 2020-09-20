package com.shkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;


//@MapperScan("com.shkj.mapper") //扫描的mapper
@SpringBootApplication
@EnableCaching //开启缓存
public class DailycollectwebApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(DailycollectwebApplication.class, args);
    }


    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DailycollectwebApplication.class);
	}
}