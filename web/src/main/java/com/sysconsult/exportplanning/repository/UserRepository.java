package com.sysconsult.exportplanning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sysconsult.exportplanning.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long id);
	User findByEmail(String email);
	List<User> findAll();
	boolean existsByEmail(String email);
}