package cn.howardliu.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 17-3-13
 *
 * @author liuxh
 * @since 1.0.0
 */
@RestController
//@EnableAutoConfiguration
public class HelloWorld {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorld.class);
    }
}
