package cn.howardliu.sl.sc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>created at 18-7-17
 *
 * @author liuxh
 * @since 0.1.0
 */
@Controller
public class IndexController {
    @RequestMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("simulateError.html")
    public String simulateError() {
        throw new RuntimeException("error page");
    }
}
