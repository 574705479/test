package com.etc.blog;

import com.etc.blog.interceptor.HomeResourceInterceptor;
import com.etc.blog.interceptor.SecurityInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.sql.PseudoColumnUsage;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
public class BlogApplication implements WebMvcConfigurer {

    @Resource
    private HomeResourceInterceptor homeResourceInterceptor;

    @Resource
    private SecurityInterceptor securityInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(homeResourceInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/admin/");
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/admin/**");

    }


}
