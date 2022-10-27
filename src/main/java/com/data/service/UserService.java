package com.data.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.data.configurations.IAuthenticationFacade;
import com.data.exceptions.DatabaseException;
import com.data.exceptions.userAlreadyExistsException;
import com.data.model.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private RoleRepository roleRepository;

	public void registerUser(User user) throws userAlreadyExistsException {
		
		if(userRepo.existsByUsername(user.getUsername())) {
			throw new userAlreadyExistsException("A user with the same username already exists in the database");
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		
		userRepo.save(user);
	}
	
	public User getUserById(long id) {
		return userRepo.findById(id).get();
	}
	
	public void deleteUserById(long id) {
		userRepo.deleteById(id);;
	}
	
	
	public User getByUsername(String username) {
		
		return userRepo.findByUsername(username);
	}
	
	
	
	public User getPrincipalUser() {
		User currentUser = new User();
		Authentication authentication = authenticationFacade.getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    currentUser = getByUsername(currentUserName);
		}
	    return currentUser;
	}
	
	
	public List<User> getAllUsers() throws DatabaseException {
		try {
			return userRepo.findAll();
		} catch(Exception e) {
			throw new DatabaseException("Oops! an error occurred while retrieving the users data");
		}
	}

	public void saveNewUser(User user) throws userAlreadyExistsException {
		if(userRepo.existsByUsername(user.getUsername())) {
			throw new userAlreadyExistsException("A user with the same username already exists in the database");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);
	}

	public void editUser(User user) {

		userRepo.save(user);

	}
}
