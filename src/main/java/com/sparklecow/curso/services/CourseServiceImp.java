package com.sparklecow.curso.services;

import com.sparklecow.curso.entities.academy.Course;
import com.sparklecow.curso.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.NoResultException;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course) {
        return null;
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new NoResultException("No result found for id " + id)
        );
    }

    @Override
    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByTitle(String title) {
        return courseRepository.findByTitle(title).orElseThrow(
                () -> new NoResultException("No result found for title " + title)
        );
    }
}
