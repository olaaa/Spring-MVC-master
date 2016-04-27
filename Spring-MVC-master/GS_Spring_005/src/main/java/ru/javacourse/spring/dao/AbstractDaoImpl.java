package ru.javacourse.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * выполнятся в транзакции будут все public методы
 * @param <T>
 */
@Transactional
public class AbstractDaoImpl<T> implements AbstractDao<T> {

    private Class<T> type;

    @Autowired
    protected HibernateTemplate ht;

    public AbstractDaoImpl(Class<T> type) {
        this.type = type;
    }

    public T getById(Integer id) {
        return ht.get(type, id);
    }

    public List<T> findAll() {
        return ht.loadAll(type);
    }

    public T create(T entyty) {
        ht.save(entyty);
        return entyty;
    }

    public T update(T entity) {
        ht.getSessionFactory().getCurrentSession().clear();
        ht.saveOrUpdate(entity);
        return entity;
    }

    public void delete(T entity) {
        ht.delete(entity);
    }
}
