package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.DefaultEventPermissionDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.DefaultEventPermission;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultEventPermissionDaoImpl extends GenericDaoImpl<DefaultEventPermission, Integer> implements DefaultEventPermissionDao {

    public DefaultEventPermissionDaoImpl() {
        super(DefaultEventPermission.class);
    }
}
