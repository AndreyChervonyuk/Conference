package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.model.Event;
import com.edu.chdtu.conference.dao.EventDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event, Integer> implements EventDao {

    public EventDaoImpl() {
        super(Event.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Event> findAll() {
        return createCriteria()
                .addOrder(Order.desc("startDate"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }
}
