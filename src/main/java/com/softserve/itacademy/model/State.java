package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "role_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    private final String nameRegex = "[\\s\\w/]+";

    @NotBlank(message = "State name cannot be blank")
    @Size(min = 1, max = 20)
    @Pattern(regexp = nameRegex)
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "state")
    private List<Task> taskList;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
