package cn.howardliu.spring.learning.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br>created at 18-2-21
 *
 * @author liuxh
 * @since 0.1.0
 */
public class ErrorInfo<T> {
    private static final Logger logger = LoggerFactory.getLogger(ErrorInfo.class);

    public static final Integer OK = 0;
    public static final Integer ERROR = -1;

    private Integer status;
    private String desc;
    private String url;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
