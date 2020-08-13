package com.putao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-13 19:57
 **/
@SpringBootApplication
@EnableEurekaServer
public class PutaoRegistryApplication {

  public static void main(String[] args) {
    SpringApplication.run(PutaoRegistryApplication.class);
  }
}
