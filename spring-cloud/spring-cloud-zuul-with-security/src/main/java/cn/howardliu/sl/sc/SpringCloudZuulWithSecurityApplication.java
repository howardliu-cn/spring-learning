package cn.howardliu.sl.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <br>created at 18-7-17
 *
 * @author liuxh
 * @since 0.1.0
 */
@SpringBootApplication
//@EnableWebSecurity
@EnableZuulProxy
public class SpringCloudZuulWithSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulWithSecurityApplication.class, args);
    }
}
