package com.putao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-13 20:21
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class PutaoItemApplication {

  public static void main(String[] args) {
    SpringApplication.run(PutaoItemApplication.class);
  }
}
