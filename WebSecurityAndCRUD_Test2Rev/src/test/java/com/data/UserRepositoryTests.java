package com.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.data.model.User;
import com.data.repository.UserRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateuser()
	{
		User user = new User();
		user.setEmail("elie@gmail.com");
		user.setPassword("elie");
		user.setName("Elie Mambou");
		
		User savedUser= repo.save(user);
		
		User user2 = new User();
		user2.setEmail("ely@gmail.com");
		user2.setPassword("elie");
		user2.setName("Elie Mambou");
		
		User savedUser2= repo.save(user2);
		
		User existUser =  entityManager.find(User.class, savedUser.getId());
		
		assertThat(user2.getEmail()).isEqualTo(existUser.getEmail());
		
		assertThat(user2.getName()).isEqualTo(existUser.getName());
	}
	
	/*@Test
	public void testFindByEmail()
	{
		String email="elie@gmail.com";
		User user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}*/
}
