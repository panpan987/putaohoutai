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
 * @create: 2020-08-13 20:21
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.putao.item.mapper")
public class PutaoItemApplication {

  public static void main(String[] args) {
    SpringApplication.run(PutaoItemApplication.class);
  }

  @Bean
  public IdWorker idWorker() {
    return new IdWorker(1, 2);
  }
}
