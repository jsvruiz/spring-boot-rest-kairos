package com.kairosds.practice.webservice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 
 * 
 * @author jsvruiz
 * 
 * This class is an entity, that represent to a Person and will be used for the persistence with the H2 DB
 *
 */


@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull(message="the name field can't be null")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message ="the name field is incorrect")
	@Column(name = "name")
	private String name;
	
	@NotNull(message="the gender field can't be null")
	@Pattern(regexp = "^[M|F]{1}$", message ="the gender field must be M or F")
	private String gender;
	
	@NotNull(message="the birthdate field can't be null")
	@Column(name = "birthdate")
	private LocalDate birthdate;
	
	@NotNull(message="the nationality field can't be null")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message ="the nationality is incorrect")
	@Column(name = "nationality")
	private String nationality;
	
	@NotNull(message="the phoneNumber field can't be null")
	@Pattern(regexp = "\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "Please enter a valid phone number: XXX-XXX-XX-XX")
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@NotNull(message="the email field can't be null")
	@Email(message="the email is incorrect")
	@Column(name = "email")
	private String email;
	
	public Person(){}
	
	public Person(String name, String gender, LocalDate birthdate, String nationality, String phoneNumber, String email) {
		this.name = name;
		this.gender = gender;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
