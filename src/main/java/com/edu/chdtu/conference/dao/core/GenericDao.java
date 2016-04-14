package com.edu.chdtu.conference.dao.core;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T, PK extends Serializable> {
    PK create(T o);
    void update(T o);
    void delete(T o);
    void delete(PK pk);
    List<T> findAll();
    T findById(PK id);
    T findBy(String field, Object value);
    List<T> findAllBy(String field, Object value);
}
