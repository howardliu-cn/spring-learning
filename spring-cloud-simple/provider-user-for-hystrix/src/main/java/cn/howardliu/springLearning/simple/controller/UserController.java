package cn.howardliu.springLearning.simple.controller;

import cn.howardliu.springLearning.simple.dao.UserRepository;
import cn.howardliu.springLearning.simple.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }
}
