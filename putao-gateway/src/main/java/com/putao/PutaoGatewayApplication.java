package com.putao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-13 20:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class PutaoGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(PutaoGatewayApplication.class);
  }
}
