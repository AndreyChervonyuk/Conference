package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.RoleDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

}
