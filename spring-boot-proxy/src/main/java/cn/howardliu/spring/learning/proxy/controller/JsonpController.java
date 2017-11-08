package cn.howardliu.spring.learning.proxy.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>created at 17-11-1
 *
 * @author liuxh
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "jsonp")
public class JsonpController {
    @RequestMapping("echo")
    @ResponseBody
    public JSONObject echo(String param) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("param", param);
        return json;
    }

    @RequestMapping("cookie/echo")
    @ResponseBody
    public JSONObject cookieEcho(String param, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("param", param);
        String timestamp = System.currentTimeMillis() + "";
        json.put("timestamp", timestamp);
        json.put("cookies", request.getCookies());
        Cookie cookie = new Cookie(timestamp, timestamp);
        cookie.setDomain(System.getenv("THIS_DOMAIN"));
        cookie.setMaxAge(60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return json;
    }
}
