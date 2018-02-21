package cn.howardliu.spring.learning.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <br>created at 18-2-22
 *
 * @author liuxh
 * @since 0.1.0
 */
public class CannotHaveBlankValidator implements ConstraintValidator<CannotHaveBlank, String> {
    @Override
    public void initialize(CannotHaveBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.contains(" ")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
