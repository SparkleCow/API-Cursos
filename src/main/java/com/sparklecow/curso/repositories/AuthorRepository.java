package com.sparklecow.curso.repositories;

import com.sparklecow.curso.entities.academy.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    List<Author> findAllByFirstName(String name);
    List<Author> findAllByLastName(String name);
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Author> findByEmail(String email);
}
