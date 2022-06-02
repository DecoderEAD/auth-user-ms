package com.ead.authuserms.validation.impl;

import com.ead.authuserms.validation.UserNameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameConstraintImpl implements ConstraintValidator<UserNameConstraint, String> {
    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if(userName == null || userName.trim().isEmpty() || userName.contains(" ")) {
            return false;
        }
        return true;
    }
}
