package com.ConnectED.Profile.service;

import java.util.List;

import com.ConnectED.Profile.model.Profile;
//import com.test.profile.entity.Image;

public interface ProfileService {
	

		Profile saveOrUpdate(Profile profile);
		Profile getByEmail(String email);
		void deleteByEmail(String email);

}
