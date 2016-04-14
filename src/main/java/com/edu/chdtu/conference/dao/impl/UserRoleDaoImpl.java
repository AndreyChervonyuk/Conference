package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.UserRoleDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole, Integer> implements UserRoleDao {

    public UserRoleDaoImpl() {
        super(UserRole.class);
    }
}
