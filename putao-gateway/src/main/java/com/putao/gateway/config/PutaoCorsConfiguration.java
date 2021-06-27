package com.putao.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-13 22:27
 **/
@Configuration
public class PutaoCorsConfiguration {

  @Bean
  public CorsFilter corsFilter() {

    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("http://putao6379.top");
    configuration.addAllowedOrigin("http://www.putao6379.top");
    configuration.addAllowedOrigin("http://manage.putao6379.top");
    configuration.addAllowedOrigin("http://pilibili.cn");
    configuration.addAllowedOrigin("http://www.pilibili.cn");
    configuration.addAllowedOrigin("http://manage.pilibili.cn");
    configuration.setAllowCredentials(true);
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");


    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    configurationSource.registerCorsConfiguration("/**", configuration);

    return new CorsFilter(configurationSource);
  }
}
