package com.edu.chdtu.conference.security;

import com.edu.chdtu.conference.service.EventPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class UserPermission implements Permission {

    Logger logger = LoggerFactory.getLogger(UserPermission.class);

    @Autowired
    EventPermissionService eventPermissionService;


    @Override
    public boolean isAllowed(Authentication authentication, Object targetDomainObject, Object permission) {
        if (isAdmin(authentication)) {
            return true;
        }

        else if (isStoryId(targetDomainObject)) {
            return eventPermissionService.hasPermission((Integer)targetDomainObject, authentication, (String)permission);
        }

        return false;
    }

    private boolean isStoryId(Object targetDomainObject) {
        return targetDomainObject instanceof Integer && (Integer) targetDomainObject > 0;
    }

    public boolean isAdmin(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}

