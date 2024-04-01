package com.sparklecow.curso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="courses")
public class Course extends BaseEntity{
    private String title;
    private String description;
    @ManyToMany(mappedBy = "courses")
    private List<Author> authors = new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Section> sections = new ArrayList<>();
}
