package com.example.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;


@EnableAutoConfiguration
public class WebsocketSourceConfiguration {
    @Bean 
    ServletWebServerFactory servletWebServerFactory(){
    return new TomcatServletWebServerFactory();
    }
  }