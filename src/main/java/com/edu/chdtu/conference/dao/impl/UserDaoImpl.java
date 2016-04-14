package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, String> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public User findByEmail(String email) {
		return (User) createCriteria()
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

}