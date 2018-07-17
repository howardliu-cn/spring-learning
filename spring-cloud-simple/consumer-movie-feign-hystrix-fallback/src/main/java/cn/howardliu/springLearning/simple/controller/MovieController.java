package cn.howardliu.springLearning.simple.controller;

import cn.howardliu.springLearning.simple.client.UserFeignClient;
import cn.howardliu.springLearning.simple.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 17-9-14
 *
 * @author liuxh
 * @since 1.0.0
 */
@RestController
public class MovieController {
    private final LoadBalancerClient loadBalancerClient;
    private final UserFeignClient userFeignClient;

    @Autowired
    public MovieController(LoadBalancerClient loadBalancerClient, UserFeignClient userFeignClient) {
        this.loadBalancerClient = loadBalancerClient;
        this.userFeignClient = userFeignClient;
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/log-instance")
    public ServiceInstance logUserInstance() {
        return this.loadBalancerClient.choose("simple-provider-user");
    }
}
