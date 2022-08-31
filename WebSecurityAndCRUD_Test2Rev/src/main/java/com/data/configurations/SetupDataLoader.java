package com.data.configurations;

import java.sql.Date;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.data.model.Role;
import com.data.model.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");

		if(!userRepository.existsByUsername("test1")) {
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		User user = new User();
		user.setDateOfBirth(Date.valueOf("1999-09-09"));
		user.setPassword(passwordEncoder.encode("test"));
		user.setEmail("test@test.com");
		user.setRoles(Arrays.asList(adminRole));
		user.setName("Test Test");
		user.setUsername("test1");
		user.setConfirmPassword(passwordEncoder.encode("test"));
		userRepository.save(user);

		alreadySetup = true;
		}
		
		if(!userRepository.existsByUsername("ramicha")) {
			Role userRole = roleRepository.findByName("ROLE_USER");
			User user = new User();
			user.setDateOfBirth(Date.valueOf("1999-09-09"));
			user.setPassword(passwordEncoder.encode("testtest"));
			user.setEmail("test@test.com");
			user.setRoles(Arrays.asList(userRole));
			user.setName("Test Test");
			user.setUsername("ramicha");
			user.setConfirmPassword(passwordEncoder.encode("testtest"));
			userRepository.save(user);

			alreadySetup = true;
			}
	}

	
	@Transactional
	Role createRoleIfNotFound(String name) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			roleRepository.save(role);
		}
		return role;
	}
}
