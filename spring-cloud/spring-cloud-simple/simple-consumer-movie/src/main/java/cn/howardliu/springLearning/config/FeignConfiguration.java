package cn.howardliu.springLearning.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br>created at 17-9-15
 *
 * @author liuxh
 * @since 1.0.0
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
