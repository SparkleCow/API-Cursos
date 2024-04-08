package com.sparklecow.curso.repositories;

import com.sparklecow.curso.entities.academy.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer>{
}
