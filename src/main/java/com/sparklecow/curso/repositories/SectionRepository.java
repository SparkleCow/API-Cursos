package com.sparklecow.curso.repositories;

import com.sparklecow.curso.entities.academy.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
