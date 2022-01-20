package com.springbootmultithreading.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootmultithreading.model.User;

@Repository
public interface UserRepositary extends JpaRepository<User, Integer> {

}
