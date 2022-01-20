package com.springbootmultithreading.contoller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springbootmultithreading.model.User;
import com.springbootmultithreading.service.UserServiceImpl;
import com.springbootmultithreading.serviceImpl.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/users", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> getFiles(@RequestParam(value = "file") MultipartFile[] files) throws Exception {
		for (MultipartFile file : files) {
			userService.addUserDetails(file);
		}
		return new ResponseEntity<String>("Details are stored successfully in the database", HttpStatus.CREATED);
	}

	@GetMapping("/getUsers")
	public CompletableFuture<ResponseEntity<?>> getAllUsers() throws Exception {
		return userService.getAllUsers().thenApply(ResponseEntity::ok);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<?> getUsers() throws Exception {
		CompletableFuture<List<User>> user1 = userService.getAllUsers();
		CompletableFuture<List<User>> user2 = userService.getAllUsers();
		CompletableFuture<List<User>> user3 = userService.getAllUsers();
		CompletableFuture.allOf(user1, user2, user3).join();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
