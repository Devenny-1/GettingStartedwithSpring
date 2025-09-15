package com.tacos.tacocloud.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

// import jakarta.validation.constraints.NotNull;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    // this configuration class is a view controller pg 83
    // it uses the below method to allows you to register one or more view controllers.
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }
}
