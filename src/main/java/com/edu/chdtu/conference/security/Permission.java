package com.edu.chdtu.conference.security;

import org.springframework.security.core.Authentication;


public interface Permission {
    boolean isAllowed(Authentication authentication, Object targetDomainObject, Object permission);
}
