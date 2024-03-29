package com.test.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.profile.entity.User;
@Repository
public interface userRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
