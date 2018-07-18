package cn.howardliu.sl.sc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>created at 18-7-18
 *
 * @author liuxh
 * @since 0.1.0
 */
@Controller
public class LoginController {
    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
