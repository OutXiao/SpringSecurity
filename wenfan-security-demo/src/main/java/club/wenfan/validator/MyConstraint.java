package club.wenfan.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/12 17:04
 */

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =MyConstraintValidator.class )
public @interface MyConstraint {

    String message() ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
