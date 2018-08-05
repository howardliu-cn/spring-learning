package cn.howardliu.sl.sc;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * <br>created at 18-7-17
 *
 * @author liuxh
 * @since 0.1.0
 */
@SpringBootApplication
@EnableWebSecurity
@EnableZuulProxy
public class SpringCloudZuulWithSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulWithSecurityApplication.class, args);
    }

    @Bean
    public ZuulFilter routingDebugFilter() {
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return "post";
            }

            @Override
            public int filterOrder() {
                return 999999;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                final List<String> routingDebug = (List<String>) RequestContext.getCurrentContext().get("routingDebug");
                routingDebug.forEach(System.out::println);
                return null;
            }
        };
    }

    @Bean
    public ZuulFilter authHeaderFilter() {
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return "pre";
            }

            @Override
            public int filterOrder() {
                return 0;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.getPrincipal() != null) {
                    RequestContext requestContext = RequestContext.getCurrentContext();
                    requestContext.addZuulRequestHeader("X-AUTH-ID", authentication.getPrincipal().toString());
                }
                return null;
            }
        };
    }
}
