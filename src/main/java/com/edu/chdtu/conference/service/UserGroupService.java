package com.edu.chdtu.conference.service;

import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.UserGroup;
import org.springframework.security.core.Authentication;

import java.util.Set;


public interface UserGroupService extends GenericService<UserGroup, Integer> {
    Set<String> getHierarchyGroups(String group);
    String getUserGroup(Integer eventId, Authentication authentication);
}
