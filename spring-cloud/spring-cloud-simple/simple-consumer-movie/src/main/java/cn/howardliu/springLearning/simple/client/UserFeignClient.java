package cn.howardliu.springLearning.simple.client;

import cn.howardliu.springLearning.config.FiegnConfiguration;
import cn.howardliu.springLearning.simple.pojo.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <br>created at 17-9-15
 *
 * @author liuxh
 * @since 1.0.0
 */
@FeignClient(name = "simple-provider-user", configuration = FiegnConfiguration.class)
public interface UserFeignClient {
    @RequestLine("GET /{id}")
    User findById(@Param("id") Long id);
}
