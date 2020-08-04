package com.nmiles;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setRegisterShutdownHook(false);
        ConfigurableApplicationContext applicationContext = app.run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("Got a sigterm, sleeping for 10m");
            try {
                Thread.sleep(Duration.ofMinutes(10).toMillis());
            } catch (InterruptedException e) {
                // do nothing
            }
            LOG.info("Shutting down for good now");
            applicationContext.close();
        }));
    }
}
