package cn.howardliu.spring.learning.api.gateway.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br>created at 18-3-6
 *
 * @author liuxh
 * @since 0.1.0
 */
public class Response<T> {
    private int code = 200;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response() {
    }

    public Response(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WrapperResponse{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
