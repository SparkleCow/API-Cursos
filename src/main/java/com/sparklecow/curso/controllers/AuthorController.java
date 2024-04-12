package com.sparklecow.curso.controllers;

import com.sparklecow.curso.entities.academy.Author;
import com.sparklecow.curso.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.NoResultException;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        authorService.create(author);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) throws NoResultException{
        return ResponseEntity.ok(authorService.findById(id));
    }
}
