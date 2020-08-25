package com.putao;

import com.putao.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-21 00:27
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.putao.user.mapper")
public class PutaoUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(PutaoUserApplication.class, args);
  }

  @Bean
  public IdWorker idWorker() {
    return new IdWorker(1, 1);
  }

}
