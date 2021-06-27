package com.putao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-26 00:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PutaoAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(PutaoAuthApplication.class);
  }
}
