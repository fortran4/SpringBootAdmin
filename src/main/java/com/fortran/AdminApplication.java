package com.fortran;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 10:05
 * @description:
 */
@SpringBootApplication
public class AdminApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.banner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                // TODO: 2016-08-02
            }
        });
        return application.sources(AdminApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
