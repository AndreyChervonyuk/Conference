package com.edu.chdtu.conference.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.edu.chdtu.conference.dao.RoleDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.User;
import com.edu.chdtu.conference.model.UserRole;
import com.edu.chdtu.conference.service.UserService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends GenericServiceImpl<User, String> implements UserDetailsService, UserService {

	private UserDao userDao;
	private RoleDao roleDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserDetailsServiceImpl(GenericDao<User, String> genericDao,
								  RoleDao roleDao,
								  PasswordEncoder passwordEncoder) {
		super(genericDao);
		this.userDao = (UserDao) genericDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(user, roleDao.findBy("name", "ROLE_USER"));
		userRoles.add(userRole);
		user.setUserRole(userRoles);
		return userDao.create(user);
	}

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		User user = findByEmail(email);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		return buildUserForAuthentication(user, authorities);
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> grantedAuthorities = userRoles.stream()
				.map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
				.collect(Collectors.toSet());

		return new ArrayList<>(grantedAuthorities);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
}