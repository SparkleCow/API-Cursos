package com.sparklecow.curso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "author")
public class Author extends BaseEntity{

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    @ManyToMany
    @JoinTable(name="authors_courses",
            joinColumns = @JoinColumn(name="author_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    private List<Course> courses = new ArrayList<>();
}
