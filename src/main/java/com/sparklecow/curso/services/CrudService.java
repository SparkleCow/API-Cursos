package com.sparklecow.curso.services;

public interface CrudService<T, D> {
    T create(T t);
    T update(D d);
    void delete(Long id);
    T findById(Long id);
    Iterable<T> findAll();
}
