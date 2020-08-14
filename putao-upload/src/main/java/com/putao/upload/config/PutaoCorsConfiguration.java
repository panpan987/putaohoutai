package com.putao.upload.config;

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
    //允许跨域的域名,如果要携带cookie,不能写*
    configuration.addAllowedOrigin("http://manage.putao.com/");
    configuration.setAllowCredentials(true);
    configuration.addAllowedMethod("*");//代表所有的请求方法...
    configuration.addAllowedHeader("*");//允许携带任何头信息

    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    configurationSource.registerCorsConfiguration("/**",configuration);

    return new CorsFilter(configurationSource);
  }

}
