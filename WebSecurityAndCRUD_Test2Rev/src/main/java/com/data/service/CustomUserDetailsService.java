package com.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.data.model.CustomUserDetails;
import com.data.model.User;
import com.data.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException{
		User user = userRepo.findByUsername(email);
				if (user==null )
				{throw new UsernameNotFoundException ("User not found");
				}
		return new CustomUserDetails(user);
	}
}
