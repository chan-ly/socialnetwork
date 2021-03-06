package com.chan.socialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SocialnetworkApplication extends SpringBootServletInitializer {

    @Override
    /*使用外置的tomcat启动*/
    /*???*/
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SocialnetworkApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SocialnetworkApplication.class, args);
    }

}
