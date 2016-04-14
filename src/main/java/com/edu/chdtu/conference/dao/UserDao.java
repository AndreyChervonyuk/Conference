package com.edu.chdtu.conference.dao;

import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.User;

public interface UserDao extends GenericDao<User, String> {
		User findByEmail(String email);
}