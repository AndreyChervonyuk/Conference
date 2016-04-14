package com.edu.chdtu.conference.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    private UserPermission userPermission;

    Logger logger = LoggerFactory.getLogger(PermissionEvaluatorImpl.class);

    @Autowired
    public PermissionEvaluatorImpl(UserPermission userPermission) {
        this.userPermission = userPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean hasPermission = false;
        if (canHandle(authentication, targetDomainObject, permission)) {
            hasPermission = userPermission.isAllowed(authentication, targetDomainObject, permission);
        }
        return hasPermission;
    }

    private boolean canHandle(Authentication authentication, Object targetDomainObject, Object permission) {
        return targetDomainObject != null && authentication != null && permission instanceof String;
    }


    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new RuntimeException("Id and Class permissions are not supperted by " + this.getClass().toString());
    }

}
