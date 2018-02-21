package cn.howardliu.spring.learning.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * <br>created at 18-2-21
 *
 * @author liuxh
 * @since 0.1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(Exception e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setDesc(e.getMessage());
        r.setStatus(ErrorInfo.ERROR);
        return r;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo<String> handle(MethodArgumentNotValidException exception) {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setDesc(StringUtils.join(
                exception.getBindingResult().getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()),
                ", "
        ));
        r.setStatus(ErrorInfo.ERROR);
        return r;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo<String> handle(ConstraintViolationException exception) {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setDesc(StringUtils.join(
                exception.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList()),
                ", "
        ));
        r.setStatus(ErrorInfo.ERROR);
        return r;
    }
}
