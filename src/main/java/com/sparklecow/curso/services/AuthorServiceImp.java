package com.sparklecow.curso.services;

import com.sparklecow.curso.entities.Author;
import com.sparklecow.curso.repositories.AuthorRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService{

    private final AuthorRepository authorRepository;

    @Override
    public Iterable<Author> findByFirstName(String firstName) {
        return authorRepository.findAllByFirstName(firstName);
    }

    @Override
    public Iterable<Author> findByLastName(String lastName) {
        return authorRepository.findAllByLastName(lastName);
    }

    @Override
    public Iterable<Author> findByFirstNameOrLastName(String firstName, String lastName) {
        return authorRepository.findAllByFirstNameContainingOrLastNameContaining(firstName, lastName);
    }

    @Override
    public Author findByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(
                () -> new NoResultException("No result found for " + firstName + " " + lastName + " combination")
        );
    }

    @Override
    public Author findByEmail(String email) {
        return authorRepository.findByEmail(email).orElseThrow(
                () -> new NoResultException("No result found for "+email+" in Authors")
        );
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(
                () -> new NoResultException("No result found for id " + id)
        );
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}
