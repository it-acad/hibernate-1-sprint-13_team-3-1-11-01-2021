package com.softserve.itacademy.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {

    @Test
    void constraintViolationOnEmptyTitle() {
        ToDo todo = new ToDo();
        todo.setTitle("");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todo);
        assertEquals(1, violations.size());
        todo.setTitle("asd@#%#@%");
        violations = validator.validate(todo);
        assertEquals(1, violations.size());
    }

    private static Stream<Arguments> provideInvalidTitle(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid")
        );
    }

}
