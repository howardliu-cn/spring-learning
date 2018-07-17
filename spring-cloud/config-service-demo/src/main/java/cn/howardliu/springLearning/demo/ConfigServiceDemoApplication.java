package cn.howardliu.springLearning.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <br>created at 17-10-9
 *
 * @author liuxh
 * @since 1.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigServiceDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceDemoApplication.class, args);
    }
}
