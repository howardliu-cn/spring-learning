package cn.howardliu.sl.sc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 18-7-18
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
public class ApiController {
    @RequestMapping("api/test")
    public String apiTest() {
        return "api test";
    }
}
