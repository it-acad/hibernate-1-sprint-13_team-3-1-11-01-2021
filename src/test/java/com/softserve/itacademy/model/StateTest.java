package com.softserve.itacademy.model;

import com.softserve.itacademy.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class StateTest {

    @Test
    void validState(){
        State state = new State();
        state.setName("in progress");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(0, violations.size());
    }

    @Test
    void checkToString(){
        State state = new State();
        state.setName("in progress");
        assertEquals("State {id = 0, name = 'in progress'} ", state.toString());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void constraintViolationStateName(String input, String errorValue) {
        State state = new State();
        state.setName(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidName(){
        return Stream.of(
                Arguments.of("invalidinvalidinvalidinvalidinvalid", "invalidinvalidinvalidinvalidinvalid"),
                Arguments.of("", ""),
                Arguments.of("Invalid84256*^%@#", "Invalid84256*^%@#")
        );
    }
}