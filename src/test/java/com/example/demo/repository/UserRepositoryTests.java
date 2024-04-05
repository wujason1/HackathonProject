package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
    public void tearDown() {
		userRepository.deleteAll();
    }
	
	@Test
	public void canSaveUser() {
		//Arrage
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		
		//Act
		User savedUser = userRepository.save(user);
		
		//Assert
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getEmail()).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void canFindListOfUsers() {
		//Arrage
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		User user2 = User.builder().email("test2@test.com").password("test2").firstName("first").lastName("last").build();
		
		User savedUser = userRepository.save(user);
		User savedUser2 = userRepository.save(user2);
		
		//Act
		List<User> userList = userRepository.findAll();
		
		//Assert
		Assertions.assertThat(userList).isNotNull();
		Assertions.assertThat(userList.size()).isEqualTo(2);
	}
	
	@Test
	public void findOneUser() {
		//Arrage
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		User savedUser = userRepository.save(user);
	
		//Act
		Optional<User> returnedUser = userRepository.findById(user.getId());
		
		//Assert
		Assertions.assertThat(returnedUser).isNotNull();
	}
	
	
	@Test
	public void canDeleteUser() {
		//Arrage
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		userRepository.save(user);
		
		//Act
		userRepository.deleteById(user.getId());
		Optional<User> returnedUser = userRepository.findById(user.getId());
		
		//Assert
		Assertions.assertThat(returnedUser).isEmpty();
		
	}
	
	@Test
	public void canFindUserByEmailAndPass() {
		//Arrage
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		userRepository.save(user);
		
		//Act
		User returnedUser = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		
		//Assert
		Assertions.assertThat(returnedUser).isNotNull();
		Assertions.assertThat(returnedUser.getEmail()).isEqualTo(user.getEmail());
		
	}
	
	@Test
	public void canFindUserByEmail() {
		//Arrage
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		userRepository.save(user);
		
		//Act
		User returnedUser = userRepository.findUserByEmail(user.getEmail());
		
		//Assert
		Assertions.assertThat(returnedUser).isNotNull();
		Assertions.assertThat(returnedUser.getEmail()).isEqualTo(user.getEmail());
	}
}
