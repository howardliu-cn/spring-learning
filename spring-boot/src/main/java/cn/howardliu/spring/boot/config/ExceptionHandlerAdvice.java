package cn.howardliu.spring.boot.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>created at 17-4-25
 *
 * @author liuxh
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    MappingJackson2JsonView jsonView = new MappingJackson2JsonView();

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        if (e instanceof IllegalArgumentException) {
            result.put("status", 403);
            response.setStatus(403);
        } else {
            result.put("status", 500);
            response.setStatus(500);
        }
        result.put("message", e.getMessage());
        result.put("path", request.getRequestURI());
        return new ModelAndView(jsonView, result);
    }
}
