package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "role_sequence"),
                    @Parameter(name = "initial_value", value = "50"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;

    @NotBlank(message = "Task name cannot be blank")
    @Size(min = 3, max = 200)
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private ToDo todo;

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public State getState() {
        return state;
    }

}
