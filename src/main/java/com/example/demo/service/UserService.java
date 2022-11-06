package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	
	public User findUserById(Long userId) {
		
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Este usuário não existe!"));
	}
	
	public User saveUser(User user) {
		
		userRepository.save(user);
		return user;
	}
	
	public void deleteUser(Long userId) {
		
		userRepository.deleteById(userId);
	}
}
