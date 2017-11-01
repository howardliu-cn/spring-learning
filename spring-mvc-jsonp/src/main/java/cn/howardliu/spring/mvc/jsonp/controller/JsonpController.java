package cn.howardliu.spring.mvc.jsonp.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>created at 17-11-1
 *
 * @author liuxh
 * @since 1.0.0
 */
@Controller
@RequestMapping("jsonp")
public class JsonpController {
    @RequestMapping("echo")
    public JSONObject echo(String param) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("param", param);
        return json;
    }
}
