package com.edu.chdtu.conference.dao.core;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class GenericDaoImpl<T, PK extends Serializable>  implements GenericDao<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entity;

    public GenericDaoImpl(Class<T> entity) {
        this.entity = entity;
    }

    public Class<T> getEntity() {
        return entity;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK create(T o) {
        return (PK) getSession().save(o);
    }

    @Override
    public void update(T o) {
        getSession().update(o);
    }

    @Override
    public void delete(T o) {
        getSession().delete(o);
    }

    @Override
    public void delete(PK pk) {
        getSession().delete(findById(pk));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return createCriteria().list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(PK pk) {
        return (T) getSession().get(entity, pk);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findBy(String field, Object value) {
        return (T) createCriteria().add(Restrictions.eq(field, value)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAllBy(String field, Object value) {
        return createCriteria().add(Restrictions.eq(field, value))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }
}
