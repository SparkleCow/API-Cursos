package com.sparklecow.curso.Entities;

import com.sparklecow.curso.entities.academy.Author;
import com.sparklecow.curso.entities.academy.Course;
import com.sparklecow.curso.entities.academy.Section;
import com.sparklecow.curso.services.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void create_and_find_courses() {
        //Crea autores de ejemplo
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("john.doe@example.com");

        //Crea cursos de ejemplo
        Course course = new Course();
        course.setTitle("Introduction to Java");
        course.setDescription("Learn the basics of Java programming");
        course.setAuthors(List.of(author));
        course.setSections(List.of(new Section()));

        //Guarda el curso en base de datos
        courseService.create(course);

        Course newCourse = courseService.findByTitle("Introduction to Java");

        //Validamos que el curso no sea nulo
        assertNotNull(newCourse);

        //Validamos que los cursos tengan la misma información
        assertEquals(newCourse.getTitle(), course.getTitle());
    }
}
