package com.softserve.itacademy;

import com.softserve.itacademy.model.*;
import com.softserve.itacademy.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@AllArgsConstructor
public class ToDoListApplication implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    ToDoRepository toDoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Running Spring Boot Application");

        Role role = roleRepository.getOne(2L);
        User validUser  = new User();
        validUser.setEmail("valid@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(role);

        validUser = userRepository.save(validUser);

        ToDo toDo = new ToDo();
        toDo.setTitle("Other");
        toDo.setOwner(validUser);
        toDo = toDoRepository.save(toDo);

        LocalDateTime localDate = toDo.getCreatedAt();
        LocalDate today = LocalDate.now();
        System.out.println(localDate.toLocalDate().equals(today));

    }
}

