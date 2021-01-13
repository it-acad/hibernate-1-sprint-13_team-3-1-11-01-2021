package com.softserve.itacademy.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskTests {


    @Test
    void validState(){
        State state = new State();
        state.setName("in progress");
        Task task= new Task();
        task.setState(state);
        task.setName("task 123");
        task.setPriority(Priority.HIGH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(0, violations.size());
    }

    @Test
    void checkToString(){
        State state = new State();
        state.setName("in progress");
        Task task= new Task();
        task.setState(state);
        task.setName("task 123");
        task.setPriority(Priority.HIGH);
        assertEquals("Task {id = 0, name = 'task 123', priority = 'HIGH', state = 'in progress'} ", task.toString());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void constraintViolationTaskName(String input, String errorValue) {
        Task task = new Task();
        task.setName(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidName(){
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("In", "In"),
                Arguments.of("I", "I")
        );
    }
}