package com.test.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.profile.entity.User;
@Repository
public interface userRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	 void deleteByEmail(String email);

}
