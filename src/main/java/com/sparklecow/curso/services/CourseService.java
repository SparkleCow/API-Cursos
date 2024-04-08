package com.sparklecow.curso.services;

import com.sparklecow.curso.entities.academy.Course;

public interface CourseService extends CrudService<Course, Course>{
    Course findByTitle(String title);
}
