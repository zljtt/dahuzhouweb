package com.zljtt.wiki.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan(basePackages = "com.zljtt")
@SpringBootApplication
public class WikiApplication {

    public static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WikiApplication.class);
        Environment environment = application.run(args).getEnvironment();
        LOG.info("Start successfully at port \thttp://{}:{}", environment.getProperty("server.address"), environment.getProperty("server.port"));
    }

}
