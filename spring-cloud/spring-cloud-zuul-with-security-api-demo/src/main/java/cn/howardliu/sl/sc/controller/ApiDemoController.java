package cn.howardliu.sl.sc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 18-7-19
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
@RequestMapping("api")
public class ApiDemoController {
    @RequestMapping("demo")
    public String demo() {
        return "demo";
    }
}
