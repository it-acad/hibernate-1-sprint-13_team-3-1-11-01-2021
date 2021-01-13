package com.softserve.itacademy;

import com.softserve.itacademy.model.*;
import com.softserve.itacademy.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.validation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class ToDoListApplication implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    ToDoRepository toDoRepository;
    TaskRepository taskRepository;
    StateRepository stateRepository;

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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

        Task task = new Task();
        task.setName("ss");
        task.setPriority(Priority.HIGH);
        State state= new State();
        state.setName("needtodo");
        stateRepository.save(state);
        task.setState(state);
        task.setTodo(toDo);
        //taskRepository.save(task);

        LocalDateTime localDate = toDo.getCreatedAt();
        LocalDate today = LocalDate.now();
        System.out.println(localDate.toLocalDate().equals(today));

    }
}

