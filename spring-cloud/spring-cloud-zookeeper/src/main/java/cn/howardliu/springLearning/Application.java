package cn.howardliu.springLearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 17-3-9
 *
 * @author liuxh
 * @since 1.0.0
 */
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class Application {
    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    public static void main(String[] args) {
        System.setProperty("spring.cloud.zookeeper.connectString", "10.6.100.1:2181");
        SpringApplication.run(Application.class, args);
    }
}
