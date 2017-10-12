package cn.howardliu.springLearning.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>created at 17-10-9
 *
 * @author liuxh
 * @since 1.0.0
 */
@RestController
public class ValueController {
    @Autowired
    private Environment env;

    @RequestMapping("from")
    public String from() {
        return env.getProperty("from", "undefined");
    }

    @RequestMapping("to")
    public String to() {
        return env.getProperty("to", "undefined");
    }

    @RequestMapping("value/{v}")
    public String value(@PathVariable("v") String v) {
        return env.getProperty(v, "undefined");
    }
}
