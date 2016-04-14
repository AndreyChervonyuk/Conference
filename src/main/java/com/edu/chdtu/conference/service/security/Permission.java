package com.edu.chdtu.conference.service.security;

import org.springframework.security.core.Authentication;


public interface Permission {
    boolean isAllowed(Authentication authentication, Object targetDomainObject, Object permission);
}
