package com.edu.chdtu.conference.service.core;

import com.edu.chdtu.conference.dao.core.GenericDao;

import java.io.Serializable;
import java.util.List;

public class GenericServiceImpl<T, PK extends Serializable>  implements GenericService<T, PK> {

    private GenericDao<T, PK> genericDao;

    public GenericServiceImpl(GenericDao<T, PK> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public PK create(T o) {
        return genericDao.create(o);
    }

    @Override
    public void update(T o) {
        genericDao.update(o);
    }

    @Override
    public void delete(T o) {
        genericDao.delete(o);
    }

    @Override
    public void delete(PK pk) {
        genericDao.delete(pk);
    }

    @Override
    public List<T> findAll() {
        return genericDao.findAll();
    }

    @Override
    public T findById(PK id) {
        return genericDao.findById(id);
    }

    @Override
    public T findBy(String field, Object value) {
        return genericDao.findBy(field, value);
    }

    @Override
    public List<T> findAllBy(String field, Object value) {
        return genericDao.findAllBy(field, value);
    }
}
