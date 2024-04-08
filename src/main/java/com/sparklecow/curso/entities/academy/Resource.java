package com.sparklecow.curso.entities.academy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

//Esta clase sera una clase padre para tres clases concretas, File, Text, Video.
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//Indicamos como queremos que se genere la tabla segun la herencia en la base de datos.
//A diferencia del otro claso de herencia "BaseEntity" la clase Resource se guardara tambien en base de datos
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int size;
    private String url;
    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
