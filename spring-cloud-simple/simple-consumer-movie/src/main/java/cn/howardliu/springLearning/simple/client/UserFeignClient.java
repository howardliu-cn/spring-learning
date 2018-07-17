package cn.howardliu.springLearning.simple.client;

import cn.howardliu.springLearning.simple.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <br>created at 17-9-15
 *
 * @author liuxh
 * @since 1.0.0
 */
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}
