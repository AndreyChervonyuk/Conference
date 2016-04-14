package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.PermissionDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission, Integer> implements PermissionDao {

    public PermissionDaoImpl() {
        super(Permission.class);
    }
}
