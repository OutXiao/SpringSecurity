package club.wenfan.validator;

import club.wenfan.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/12 17:08
 */

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {


    @Autowired
    private GreetingService greetingService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init ");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        System.out.println(o);
        greetingService.helloGreeting("tom");
        return false;
    }
}
