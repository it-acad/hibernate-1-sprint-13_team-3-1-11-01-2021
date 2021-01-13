package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
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
    private long id;

    @NotBlank(message = "The roleName cannot be empty")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[-A-Za-z0-9#]+")
    private String name;

    @OneToMany(mappedBy = "role_id")
    private List<User> users;

    public Role() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Role {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                "} ";
    }
}
