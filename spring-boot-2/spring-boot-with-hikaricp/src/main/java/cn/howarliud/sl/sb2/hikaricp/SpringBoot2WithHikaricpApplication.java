package cn.howarliud.sl.sb2.hikaricp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadPoolExecutor;

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

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setKeepAliveSeconds(200);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
