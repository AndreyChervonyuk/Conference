package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.EventPermissionDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.EventPermission;
import com.edu.chdtu.conference.model.dto.PermissionDto;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public class EventPermissionDaoImpl extends GenericDaoImpl<EventPermission, Integer> implements EventPermissionDao {

    public EventPermissionDaoImpl() {
        super(EventPermission.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EventPermission> getPermissionByUserGroups(Integer eventId, Set<String> groups) {
        return createCriteria()
                .createAlias("event", "e")
                .createAlias("userGroup", "g")
                .add(Restrictions.eq("e.id", eventId))
                .add(Restrictions.in("g.name", groups))
                .list();
    }

    @Override
    public EventPermission getByEventId(Integer eventId, Integer permissionId) {
        return (EventPermission) createCriteria()
                .createAlias("event", "e")
                .createAlias("permission", "p")
                .add(Restrictions.eq("e.id", eventId))
                .add(Restrictions.eq("p.id", permissionId))
                .uniqueResult();
    }

    @Override
    public EventPermission getEventPermission(Integer eventId, String permission, Set<String> userGroups) {
        return (EventPermission) createCriteria()
                .createAlias("event", "e")
                .createAlias("permission", "p")
                .createAlias("userGroup", "g")
                .add(Restrictions.eq("e.id", eventId))
                .add(Restrictions.eq("p.name", permission))
                .add(Restrictions.in("g.name", userGroups))
                .uniqueResult();
    }
}
