package com.acc.libraryManagement.validators;

import com.acc.libraryManagement.repository.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final MemberRepository memberRepository;

    public UniqueEmailValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true; // null values are handled by @NotBlank
        }
        return !memberRepository.existsByEmail(email);
    }
}

