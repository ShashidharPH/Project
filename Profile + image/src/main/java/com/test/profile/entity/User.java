package com.test.profile.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;
 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_user")

public class User {
	 public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	 @Lob
	    private Blob image;
	  private Date date = new Date();
	
@Column(unique = true)
private String email;

private String firstName;
private String lastName;
private String img_Url;
private String gender;
private String bio;
private String edu;
private String skill;
private String work_exp;
private String city;
private Long mob;
private String state;
private String country;
private String occupation;



public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getImg_Url() {
	return img_Url;
}
public void setImg_Url(String img_Url) {
	this.img_Url = img_Url;
}
public String getEdu() {
	return edu;
}
public void setEdu(String edu) {
	this.edu = edu;
}
//public Long getU_id() {
//	return u_id;
//}
//public void setU_id(Long u_id) {
//	this.u_id = u_id;
//}
//public String getName() {
//	return name;
//}
//public void setName(String name) {
//	this.name = name;
//}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getBio() {
	return bio;
}
public void setBio(String bio) {
	this.bio = bio;
}


public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
public String getWork_exp() {
	return work_exp;
}
public void setWork_exp(String work_exp) {
	this.work_exp = work_exp;
}
public Long getMob() {
	return mob;
}
public void setMob(Long mob) {
	this.mob = mob;
}
public String getOccupation() {
	return occupation;
}
public void setOccupation(String occupation) {
	this.occupation = occupation;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


}
