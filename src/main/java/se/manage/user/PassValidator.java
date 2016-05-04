package se.manage.user;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static se.manage.user.UserValidator.errorMessage;

public class PassValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String str = (String) target;
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]*");
        if (!pattern.matcher(str).matches()) {
            errors.rejectValue("account", null, errorMessage);
        }
    }
}
