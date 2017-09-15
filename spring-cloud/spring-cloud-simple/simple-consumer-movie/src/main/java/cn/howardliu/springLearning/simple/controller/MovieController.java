package cn.howardliu.springLearning.simple.controller;

import cn.howardliu.springLearning.simple.client.UserFeignClient;
import cn.howardliu.springLearning.simple.pojo.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 17-9-14
 *
 * @author liuxh
 * @since 1.0.0
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
    private final LoadBalancerClient loadBalancerClient;
    private final UserFeignClient userUserFeignClient;
    private final UserFeignClient adminUserFeignClient;

    @Autowired
    public MovieController(LoadBalancerClient loadBalancerClient,
            Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.loadBalancerClient = loadBalancerClient;
        this.userUserFeignClient = Feign.builder()
                .client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(UserFeignClient.class, "http://simple-provider-user/");
        this.adminUserFeignClient = Feign.builder()
                .client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(UserFeignClient.class, "http://simple-provider-user/");
    }

    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return userUserFeignClient.findById(id);
    }

    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return adminUserFeignClient.findById(id);
    }

    @GetMapping("/log-instance")
    public ServiceInstance logUserInstance() {
        return this.loadBalancerClient.choose("simple-provider-user");
    }
}
