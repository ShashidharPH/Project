package com.ConnectED.Profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ConnectED.Profile.model.Profile;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Profile findByEmail(String email);

}
