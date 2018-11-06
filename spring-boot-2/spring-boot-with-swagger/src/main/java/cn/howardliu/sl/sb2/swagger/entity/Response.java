package cn.howardliu.sl.sb2.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <br>created at 18-4-19
 *
 * @author liuxh
 * @since 0.1.0
 */
@ApiModel("返回结果")
public class Response<T> {
    @ApiModelProperty(hidden = true)
    private Boolean success;
    @ApiModelProperty(value = "返回状态码", example = "200")
    private Integer code;
    @ApiModelProperty(value = "结果描述", example = "success")
    private String desc;
    @ApiModelProperty(value = "调用uri，可能为空", example = "/url/short")
    private String uri;
    @ApiModelProperty(value = "返回结果", example = "[{...}]")
    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public Response<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Response<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Response<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Response<T> setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }
}
