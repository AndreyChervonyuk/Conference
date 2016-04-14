package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.UserGroupsDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.UserGroup;
import org.springframework.stereotype.Repository;

@Repository
public class UserGroupsDaoImpl extends GenericDaoImpl<UserGroup, Integer> implements UserGroupsDao {

    public UserGroupsDaoImpl() {
        super(UserGroup.class);
    }

}
