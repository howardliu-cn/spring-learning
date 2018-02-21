package cn.howardliu.spring.learning.validator;

/**
 * <br>created at 18-2-21
 *
 * @author liuxh
 * @since 0.1.0
 */
public class ParamInvalidException extends Exception {
    public ParamInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
