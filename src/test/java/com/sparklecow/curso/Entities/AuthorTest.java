package com.sparklecow.curso.Entities;

import com.sparklecow.curso.entities.academy.Author;
import com.sparklecow.curso.services.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorTest {

    @Autowired
        private AuthorService authorRepository;

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
        authorRepository.create(author);
        authorRepository.create(author1);

        //Busca los autores recién guardados
        Author foundAuthor = authorRepository.findByFirstNameAndLastName("John", "Doe");
        Author foundAuthor1 = authorRepository.findByEmail("sofia@example.com");

        //Verifica si los autores fueron encontrados correctamente
        assertNotNull(foundAuthor);
        assertNotNull(foundAuthor1);
        //Verifica si los autores fueron encontrados y comparar los resultados con los datos de ejemplo
        assertEquals("John", foundAuthor.getFirstName());
        assertEquals("Doe", foundAuthor.getLastName());
        assertEquals("john.doe@example.com", foundAuthor.getEmail());

        assertEquals("Sofia", foundAuthor1.getFirstName());
        assertEquals("Londoño", foundAuthor1.getLastName());
        assertEquals("sofia@example.com", foundAuthor1.getEmail());
    }
}

