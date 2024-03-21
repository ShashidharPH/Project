package com.test.profile.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.profile.entity.Image;



@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}
