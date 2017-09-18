package cn.howardliu.springLearning.simple.client;

import cn.howardliu.springLearning.simple.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <br>created at 17-9-15
 *
 * @author liuxh
 * @since 1.0.0
 */
@FeignClient(name = "simple-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}

@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(final Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                User user = new User();
                if (throwable instanceof IllegalArgumentException) {
                    user.setId(-1L);
                } else {
                    user.setId(-2L);
                }
                user.setUsername("default: " + throwable.getMessage());
                return user;
            }
        };
    }
}
