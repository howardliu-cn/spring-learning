package cn.howarliud.sl.sb2.hikaricp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <br>created at 18-6-27
 *
 * @author liuxh
 * @since 0.1.0
 */
@SpringBootApplication
public class SpringBoot2WithHikaricpApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2WithHikaricpApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
