package com.softserve.itacademy.model;

import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ToDoTests {
    private static Role traineeRole;
    private static User validUser;

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @ParameterizedTest
    @MethodSource("provideInvalidTitle")
    void constraintViolationRoleName(String input, String errorValue) {
        ToDo toDo = new ToDo();
        toDo.setTitle(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidTitle(){
        return Stream.of(
                Arguments.of("invalid$^$^", "invalid$^$^"),
                Arguments.of("", "")
        );
    }

    @Test
    void checkToString(){
        ToDo toDo = new ToDo();
        toDo.setTitle("Other");
        assertEquals("ToDo {id = 0, name = 'Other'} ", toDo.toString());
    }

    @Test
    void checkCorrectCreatedAt(){
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");
        roleRepository.save(traineeRole);
        validUser  = new User();
        validUser.setEmail("validasd@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(traineeRole);
        userRepository.save(validUser);

        ToDo toDo = new ToDo();
        toDo.setTitle("Other");
        toDo.setOwner(validUser);
        toDo = toDoRepository.save(toDo);

        LocalDateTime localDate = LocalDateTime.now();
        assertEquals(toDo.getCreatedAt().toLocalDate(), localDate.toLocalDate());
    }



}