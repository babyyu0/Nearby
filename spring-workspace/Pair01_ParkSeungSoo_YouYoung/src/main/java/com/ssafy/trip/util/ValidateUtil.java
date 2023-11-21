package com.ssafy.trip.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


public class ValidateUtil {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private static Set<ConstraintViolation<Object>> violations;

    public static void validate(HttpStatus httpStatus, Object object) throws MyException {
        violations = validator.validate(object);

        if (!violations.isEmpty()) {  // 유효성 검사 실패 시
            for (ConstraintViolation<Object> violation : violations) {
                throw new MyException(httpStatus, violation.getMessage());
            }
        }
    }

    public static void clientValidate(Object object) throws MyException {
        validate(BAD_REQUEST, object);
    }

    public static void serverValidate(Object object) throws MyException {
        validate(INTERNAL_SERVER_ERROR, object);
    }
}
