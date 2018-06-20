package cn.howardliu.spring.learning.validator;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <br>created at 18-2-21
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
@Validated
public class ValidatorController {
    @RequestMapping("simple")
    public JSONObject simple(@NotNull(message = "用户名不能为空") @RequestParam(required = false) String username,
            @NotBlank(message = "密码不能为空") @Length(min = 6, message = "密码长度不能小于6") String password) {
        JSONObject result = new JSONObject();
        result.put("username", username);
        result.put("password", password);
        return result;
    }

    @RequestMapping("params")
    public JSONObject params(@Validated User user, BindingResult bindingResult) throws ParamInvalidException {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new ParamInvalidException(
                    StringUtils
                            .join(
                                    allErrors.stream()
                                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                            .collect(Collectors.toList()),
                                    ", "
                            ),
                    new IllegalArgumentException());
        }
        JSONObject result = new JSONObject();
        result.put("user", user);
        return result;
    }
}
