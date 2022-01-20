package com.springbootmultithreading.serviceImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.springbootmultithreading.model.User;

public interface UserService {

	CompletableFuture<List<User>> addUserDetails(MultipartFile file) throws Exception;

	CompletableFuture<List<User>> getAllUsers() throws Exception;

}
