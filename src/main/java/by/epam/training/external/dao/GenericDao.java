package by.epam.training.external.dao;

import by.epam.training.external.service.util.HibernateSessionFactoryUtil;
import org.hibernate.SessionFactory;

import java.util.List;

abstract class GenericDao<T> {
    SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public abstract void save(T entity);
    public abstract T findById(int id);
    public abstract List<T> findAll();
    public abstract void update(T entity);
    public abstract void delete(T entity);
}
