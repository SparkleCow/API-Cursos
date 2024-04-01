package com.sparklecow.curso.Author;

import com.sparklecow.curso.entities.Author;
import com.sparklecow.curso.repositories.AuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void create_and_find_author() {
        //Crea autores de ejemplo
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("john.doe@example.com");

        Author author1 = new Author();
        author1.setFirstName("Sofia");
        author1.setLastName("Londoño");
        author1.setEmail("sofia@example.com");

        //Guardar los autores en base de datos
        authorRepository.save(author);
        authorRepository.save(author1);

        //Busca los autores recién guardados
        Optional<Author> foundAuthor = authorRepository.findByFirstNameAndLastName("John", "Doe");
        Optional<Author> foundAuthor1 = authorRepository.findByEmail("sofia@example.com");

        //Verifica si los autores fueron encontrados correctamente
        assertTrue(foundAuthor.isPresent());
        assertTrue(foundAuthor1.isPresent());

        //Verifica si los autores fueron encontrados y comparar los resultados con los datos de ejemplo
        assertEquals("John", foundAuthor.get().getFirstName());
        assertEquals("Doe", foundAuthor.get().getLastName());
        assertEquals("john.doe@example.com", foundAuthor.get().getEmail());

        assertEquals("Sofia", foundAuthor1.get().getFirstName());
        assertEquals("Londoño", foundAuthor1.get().getLastName());
        assertEquals("sofia@example.com", foundAuthor1.get().getEmail());
    }
}

