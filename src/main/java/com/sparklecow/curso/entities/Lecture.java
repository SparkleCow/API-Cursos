package com.sparklecow.curso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="lecture")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class Lecture extends BaseEntity{
    private String name;
    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;
    @OneToOne
    @JoinColumn(name="resource_id")
    private Resource resource;
}