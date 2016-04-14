package com.edu.chdtu.conference.service;

import com.edu.chdtu.conference.model.User;
import com.edu.chdtu.conference.service.core.GenericService;


public interface UserService extends GenericService<User, String> {
    User findByEmail(String email);
}
