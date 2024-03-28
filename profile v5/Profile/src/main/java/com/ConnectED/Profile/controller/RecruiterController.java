package com.ConnectED.Profile.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ConnectED.Profile.exception.DeletedException;
import com.ConnectED.Profile.model.Recruiter;
import com.ConnectED.Profile.service.RecruiterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/{email}")
    public ResponseEntity<Recruiter> getFullProfileByEmail(@PathVariable String email) {
        Recruiter profile = recruiterService.getByEmail(email);
        if (profile != null) {
            try {
                Blob imageBlob = profile.getImage();
                
                if (imageBlob != null) {
                	byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
                    String base64Image = Base64.getEncoder().encodeToString(bytes);
                    profile.setImageBase64(base64Image);
                } else {
                	profile.setImageBase64(null);
                }
                
                
                
                return new ResponseEntity<>(profile, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> createOrUpdateProfile(
            @RequestParam(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "profile",required = false) String profileJson
    ) {
        if (profileJson == null) {
            return ResponseEntity.badRequest().body("Profile JSON is required.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Recruiter profile = objectMapper.readValue(profileJson, Recruiter.class);

            if (file != null) {
                byte[] bytes = file.getBytes();
                Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
                profile.setImage(imageBlob);
            }

            Recruiter savedProfile = recruiterService.save(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteProfileByEmail(@PathVariable String email) {
        try {
            recruiterService.deleteByEmail(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DeletedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateProfile(
            @PathVariable String email,
            @RequestParam(value = "image", required = false) MultipartFile file,
            @RequestParam(value = "profile", required = false) String profileJson) {

        if (profileJson == null) {
            return ResponseEntity.badRequest().body("Profile JSON is required.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Recruiter updatedProfile = objectMapper.readValue(profileJson, Recruiter.class);

            Recruiter existingProfile = recruiterService.getByEmail(email);
            if (existingProfile == null) {
                return ResponseEntity.notFound().build();
            }

            if (file != null && !file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
                existingProfile.setImage(imageBlob);
            }

            existingProfile.setFirstName(updatedProfile.getFirstName()); 
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setGender(updatedProfile.getGender());
            existingProfile.setBio(updatedProfile.getBio());
            existingProfile.setCity(updatedProfile.getCity());
            existingProfile.setCountry(updatedProfile.getCountry());
            existingProfile.setCompanyName(updatedProfile.getCompanyName());
            existingProfile.setMob(updatedProfile.getMob());
            existingProfile.setState(updatedProfile.getState());
            existingProfile.setUserName(updatedProfile.getUserName());
            
            Recruiter savedProfile = recruiterService.save(existingProfile);
            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request.");
        }
    }
}