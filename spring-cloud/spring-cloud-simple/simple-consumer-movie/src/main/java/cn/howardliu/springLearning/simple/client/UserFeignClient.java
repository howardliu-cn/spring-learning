package cn.howardliu.springLearning.simple.client;

import cn.howardliu.springLearning.config.FeignConfiguration;
import cn.howardliu.springLearning.simple.pojo.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <br>created at 17-9-15
 *
 * @author liuxh
 * @since 1.0.0
 */
@FeignClient(name = "simple-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
    @RequestLine("GET /{id}")
    User findById(@Param("id") Long id);
}
