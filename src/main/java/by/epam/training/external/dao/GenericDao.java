package by.epam.training.external.dao;

import java.util.List;

public interface GenericDao<T> {
    void save(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
