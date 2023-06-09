package com.idg.demo.filter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Auther: csp1999
 * @Date: 2020/12/08/18:57
 * @Description: 跨域相关配置
 */
// @Configuration
public class CorsConfig {
    /**
     * CORS跨域配置
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        // 1.new一个CORS配置实例
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1) 允许的域,不要写*，否则cookie就无法使用了
//        corsConfiguration.addAllowedOrigin("http://web.csp1999.com");
        // 允许的域的集合
        List<String> orginList = new ArrayList<>();
        orginList.add("http://localhost:8083");
        corsConfiguration.setAllowedOrigins(orginList);
        // 2) 是否发送Cookie信息
        corsConfiguration.setAllowCredentials(true);
        // 3) 允许的请求方式
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        // 4）允许的头信息
        corsConfiguration.addAllowedHeader("*");
        // 2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        // 3.返回新的CorsFilter.
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}