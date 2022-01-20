package com.springbootmultithreading.service;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springbootmultithreading.model.User;
import com.springbootmultithreading.repositary.UserRepositary;
import com.springbootmultithreading.serviceImpl.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepositary userRepositary;

	@Async
	public CompletableFuture<List<User>> addUserDetails(MultipartFile multipartFile) throws Exception {
		long start = System.currentTimeMillis();
		List<User> users = parseCsvfiles(multipartFile);
		log.info("Saving a list of users of size records " + users.size());
		userRepositary.saveAll(users);
		long end = System.currentTimeMillis();
		log.info("Total time---->>>" + (end - start));
		return CompletableFuture.completedFuture(users);
	}

	public List<User> parseCsvfiles(MultipartFile file) throws Exception {
		final List<User> usersList = new ArrayList<>();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String lines;
		while ((lines = bufferedReader.readLine()) != null) {
			final String[] data = lines.split(",");
			User user = new User();
			user.setName(data[0]);
			user.setEmail(data[1]);
			user.setGender(data[2]);
			usersList.add(user);
		}
		return usersList;
	}

	@Override
	@Async
	public CompletableFuture<List<User>> getAllUsers() throws Exception {
		log.info("Get list of users by---->"+Thread.currentThread().getName());
		List<User> user=userRepositary.findAll();;
		return  CompletableFuture.completedFuture(user);
	}
}
