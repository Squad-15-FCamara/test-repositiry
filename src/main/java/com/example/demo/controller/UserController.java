package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	private UserRepository userRepository;
	
	@ApiOperation(value = "fetch all users")
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	@ApiOperation(value = "fetch an user by ID")
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		
		return ResponseEntity.ok(userService.findUserById(userId));
	}
	
	@ApiOperation(value = "create an user")
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "delete an user by ID")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> removeUser(@PathVariable Long userId) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deleteUser(userId);
		
		return ResponseEntity.noContent().build();
	}
	
}
