package com.edu.chdtu.conference.service.impl;


import com.edu.chdtu.conference.dao.MemberDao;
import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.model.enums.Group;
import com.edu.chdtu.conference.service.UserGroupService;
import com.edu.chdtu.conference.dao.UserGroupsDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.UserGroup;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserGroupServiceImpl extends GenericServiceImpl<UserGroup, Integer> implements UserGroupService {

    private UserGroupsDao userGroupsDao;
    private MemberDao memberDao;

    @Autowired
    public UserGroupServiceImpl(GenericDao<UserGroup, Integer> genericDao,
                                MemberDao memberDao) {
        super(genericDao);
        this.userGroupsDao = (UserGroupsDao) genericDao;
        this.memberDao = memberDao;
    }


    @Override
    public String getUserGroup(Integer eventId, Authentication authentication) {
        String role;
        if (authentication instanceof AnonymousAuthenticationToken) {
            role = Group.NOT_MEMBERS.name();
        } else {
            Member member = memberDao.getByEventId(eventId, authentication.getName());
            if (member != null) {
                role = member.getUserGroup().getName();
            } else {
                role = Group.NOT_MEMBERS.name();
            }
        }
        return role;
    }

    public Set<String> getHierarchyGroups(String group) {
        Set<String> groups = new HashSet<>();

        switch (group) {
            case "GROUP_ADMINS":
                groups.add("GROUP_ADMINS");
            case "MEMBERS":
                groups.add("MEMBERS");
            case "WAIT" :
                groups.add("WAIT");
            case "NOT_MEMBERS":
                groups.add("NOT_MEMBERS");

        }

        return groups;
    }
}
