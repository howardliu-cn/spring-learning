package cn.howardliu.spring.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <br>created at 17-11-17
 *
 * @author liuxh
 * @since 1.0.0
 */
@Controller
@RequestMapping("web")
public class WebController {
    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
