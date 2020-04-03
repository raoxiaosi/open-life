package com.rao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * @author raojing
 * @date 2019/12/2 13:16
 */
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = "com.rao.dao")
@EnableFeignClients(basePackages = {"com.rao"})
@SpringBootApplication
public class RainSecurityAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainSecurityAuthApplication.class, args);
    }
    
}
