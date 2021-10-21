package com.virtusa.security.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.virtusa.security.model.Member;
import com.virtusa.security.service.MemberService;

@Component
public class MemberValidator implements Validator {
    @Autowired
    private MemberService memberservice;

    @Override
    public boolean supports(Class<?> aClass) {
        return Member.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Member mem = (Member) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (mem.getUsername().length() < 6 || mem.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (memberservice.findByUsername(mem.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (mem.getPassword().length() < 8 || mem.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!mem.getPasswordConfirm().equals(mem.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
