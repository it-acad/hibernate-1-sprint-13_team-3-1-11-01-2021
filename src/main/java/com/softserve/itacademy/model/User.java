package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

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


    @Pattern(regexp = "(^([A-Z])\\w+-+[A-Z](.+?)(?:\\n|$))")
    @Column
    private String first_name;

    @Pattern(regexp = "(^([A-Z])\\w+-+[A-Z](.+?)(?:\\n|$))")
    @Column
    private String last_name;


    @NotBlank(message = "The email cannot be empty")
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @Column
    private String password;


    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role_id;

    @OneToMany(mappedBy = "owner_id")
    private List<ToDo> todo;

    @ManyToMany(mappedBy = "todo_coll")
    private List<ToDo> collaborator;

    public User() {
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role_id = role;
    }

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole_id() {
        return role_id;
    }
}