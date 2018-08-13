package cn.howardliu.sl.sc.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <br>created at 18-3-29
 *
 * @author liuxh
 * @version 1.0.0
 */
@Configuration
@PropertySource(value = "classpath:mybatisDruid.properties", ignoreResourceNotFound = true)
@EnableScheduling
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableTransactionManagement
@MapperScan({
        "cn.howardliu.sl.sc.mapper"
})
@ComponentScan({
        "cn.howardliu.sl.sc"
})
public class BootConfiguration {
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
}
