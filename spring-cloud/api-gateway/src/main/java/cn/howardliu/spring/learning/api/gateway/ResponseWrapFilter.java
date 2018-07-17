package cn.howardliu.spring.learning.api.gateway;

import cn.howardliu.spring.learning.api.gateway.pojo.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <br>created at 18-3-6
 *
 * @author liuxh
 * @since 0.1.0
 */
public class ResponseWrapFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(ResponseWrapFilter.class);
    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";
    private static final String ERROR_MESSAGE_KEY = "error.message";
    private static final String ERROR_EXCEPTION_KEY = "error.exception";
    private static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";

    private static ObjectMapper mapper = new ObjectMapper();
    private static DynamicIntProperty INITIAL_STREAM_BUFFER_SIZE = DynamicPropertyFactory
            .getInstance()
            .getIntProperty(ZuulConstants.ZUUL_INITIAL_STREAM_BUFFER_SIZE, 8192);

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private ThreadLocal<byte[]> buffers = new ThreadLocal<byte[]>() {
        @Override
        protected byte[] initialValue() {
            return new byte[INITIAL_STREAM_BUFFER_SIZE.get()];
        }
    };

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.containsKey(ERROR_STATUS_CODE_KEY);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            HttpServletRequest request = ctx.getRequest();
            HttpServletResponse servletResponse = ctx.getResponse();
            if (servletResponse.getCharacterEncoding() == null) {
                servletResponse.setCharacterEncoding("UTF-8");
            }

            int statusCode = (Integer) ctx.get(ERROR_STATUS_CODE_KEY);
            String message = (String) ctx.get(ERROR_MESSAGE_KEY);
            if (ctx.containsKey(ERROR_EXCEPTION_KEY)) {
                Throwable e = (Exception) ctx.get(ERROR_EXCEPTION_KEY);
                Throwable re = getOriginException(e);
                if (re instanceof java.net.ConnectException) {
                    message = "Real Service Connection refused";
                    logger.warn("uri:{},error:{}", request.getRequestURI(), re.getMessage());
                } else if (re instanceof java.net.SocketTimeoutException) {
                    message = "Real Service Timeout";
                    logger.warn("uri:{},error:{}", request.getRequestURI(), re.getMessage());
                } else if (re instanceof com.netflix.client.ClientException) {
                    message = re.getMessage();
                    logger.warn("uri:{},error:{}", request.getRequestURI(), re.getMessage());
                } else {
                    logger.warn("Error during filtering", e);
                }
            }
            if (StringUtils.isBlank(message)) {
                message = DEFAULT_ERR_MSG;
            }
            request.setAttribute("javax.servlet.error.status_code", statusCode);
            request.setAttribute("javax.servlet.error.message", message);


            Response<Void> response = new Response<>();
            response.setCode(statusCode);
            response.setDesc(message);
            OutputStream outStream = servletResponse.getOutputStream();
            writeResponse(
                    new ByteArrayInputStream(
                            mapper.writeValueAsString(response).getBytes(servletResponse.getCharacterEncoding())
                    ),
                    outStream
            );
        } catch (Exception e) {
            String error = "Error during filtering[ErrorFilter]";
            logger.error(error, e);

            try {
                writeResponse(
                        new ByteArrayInputStream(
                                mapper.writeValueAsString(new Response<Void>(500, error)).getBytes(ctx.getResponse().getCharacterEncoding())
                        ),
                        (OutputStream) ctx.getResponse()
                );
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    private void writeResponse(InputStream zin, OutputStream out) throws Exception {
        byte[] bytes = buffers.get();
        int bytesRead;
        while ((bytesRead = zin.read(bytes)) != -1) {
            out.write(bytes, 0, bytesRead);
        }
    }

    private Throwable getOriginException(Throwable e) {
        e = e.getCause();
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e;
    }
}
