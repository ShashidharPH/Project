package com.test.profile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.profile.entity.Image;

//import com.project.model.Image;

@Service
public interface ImageService {
    public Image create(Image image);
    public List<Image> viewAll();
    public Image viewById(long id);
}
