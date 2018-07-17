package cn.howardliu.springLearning.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <br>created at 17-9-14
 *
 * @author liuxh
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApplication.class, args);
    }
}
