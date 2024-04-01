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
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="section")
public class Section extends BaseEntity{
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    @OneToMany(mappedBy = "section")
    private List<Lecture> lectures = new ArrayList<Lecture>();
}
