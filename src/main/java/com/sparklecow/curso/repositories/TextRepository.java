package com.sparklecow.curso.repositories;

import com.sparklecow.curso.entities.academy.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
}
