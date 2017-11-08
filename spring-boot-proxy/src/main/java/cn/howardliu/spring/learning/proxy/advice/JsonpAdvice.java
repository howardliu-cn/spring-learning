package cn.howardliu.spring.learning.proxy.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * <br>created at 17-11-8
 *
 * @author liuxh
 * @since 1.0.0
 */
@ControllerAdvice(basePackages = "cn.howardliu.spring.learning.proxy.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("jsonp", "callback");
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
            MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        super.beforeBodyWriteInternal(bodyContainer, contentType, returnType, request, response);
        System.err.println("jsonp advice: before body write");
    }
}
