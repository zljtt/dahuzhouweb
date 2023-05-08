package github.zljtt.dahuzhou.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan(basePackages = "github.zljtt")
@SpringBootApplication()
@MapperScan("github.zljtt.dahuzhou.mapper")
public class DahuzhouApplication {

    public static final Logger LOG = LoggerFactory.getLogger(DahuzhouApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DahuzhouApplication.class);
        Environment environment = application.run(args).getEnvironment();
        LOG.info("Start successfully at port \thttp://{}:{}", environment.getProperty("server.address"), environment.getProperty("server.port"));
    }

}
