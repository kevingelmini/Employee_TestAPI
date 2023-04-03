package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import com.example.configuration.WebsocketSourceConfiguration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication

public class DemoApplication {


        public static void main(String[] args) {
                SpringApplication.run(DemoApplication.class, args);
        }

        @Bean
        public OpenAPI openApi() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Titolo")
                                                .description("Description")
                                                .version("1.0.0")
                                                .contact(new Contact()
                                                                .name("Kevin Gelmini")
                                                                .url("https://www.google.com")
                                                                .email("kevin.gelmini@marconirovereto.it")));
        }

}
