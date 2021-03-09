package com.revistas.validation;

import javax.validation.ConstraintValidator;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;

}
