package com.sysconsult.exportplanning.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysconsult.exportplanning.model.Role;
import com.sysconsult.exportplanning.model.User;
import com.sysconsult.exportplanning.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	public User save(User oldUser) {
		User user = new User(oldUser.getFirstName(), oldUser.getLastName(), oldUser.getEmail(),
				passwordEncoder.encode(oldUser.getPassword()), oldUser.getRoles());
		return userRepo.save(user);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid userName or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getLabel())).collect(Collectors.toList());

	}
}
