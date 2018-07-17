package cn.howardliu.sl.sc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 18-7-17
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("api/test")
    public String apiTest() {
        return "api test";
    }
}
