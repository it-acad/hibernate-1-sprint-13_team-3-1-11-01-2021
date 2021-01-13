package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "todo_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @NotEmpty(message = "To-Do title cannot be empty")
    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9-]+")
    private String title;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<Task> taskList;

    @ManyToMany
    @JoinTable(
            name = "todo_collaborator",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "collaborator_id"))
    private List<User> todo_coll;

    @ManyToOne()
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner_id;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public ToDo() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getOwner() {
        return owner_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOwner(User owner) {
        this.owner_id = owner;
    }

    @Override
    public String toString() {
        return "ToDo {" +
                "id = " + id +
                ", name = '" + title + '\'' +
                "} ";
    }
}
