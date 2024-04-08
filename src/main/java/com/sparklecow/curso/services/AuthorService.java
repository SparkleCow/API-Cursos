package com.sparklecow.curso.services;

import com.sparklecow.curso.entities.academy.Author;

public interface AuthorService extends CrudService<Author, Author>{
    Iterable<Author> findByFirstName(String firstName);
    Iterable<Author> findByLastName(String lastName);
    Iterable<Author> findByFirstNameOrLastName(String firstName, String lastName);
    Author findByFirstNameAndLastName(String firstName, String lastName);
    Author findByEmail(String email);
}
