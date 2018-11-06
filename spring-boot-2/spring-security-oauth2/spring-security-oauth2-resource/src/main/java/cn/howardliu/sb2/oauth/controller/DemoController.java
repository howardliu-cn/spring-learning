package cn.howardliu.sb2.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 18-11-6
 *
 * @author liuxh
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/blog/{id}")
    public String getBlogById(@PathVariable long id) {
        return "this is blog " + id;
    }
}
