package com.kairosds.practice.webservice;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kairosds.practice.webservice.entity.Person;
import com.kairosds.practice.webservice.service.PersonService;

/**
 * 
 * 
 * @author jsvruiz
 *
 */
@SpringBootApplication
public class WebserviceApplication {

	@Autowired
	PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

	/*
	 * TODO We insert some persons only for tests and to have dummy datas
	 */
	@PostConstruct
	public void init() {
		
		// Creation for 3 persons
		Person person1 = new Person("Jesus Vazquez Ruiz", "M", LocalDate.of(1986, 9, 11), "Mexican", "552-124-12-33",
				"jvruiz@outlook.com");
		Person person2 = new Person("Diana Perez Rodriguez", "F", LocalDate.of(1991, 8, 23), "Mexican", "553-104-32-23",
				"agirl@outlook.com");
		Person person3 = new Person("Pedro Diaz Hernandez", "M", LocalDate.of(1981, 12, 6), "Mexican", "552-234-31-87",
				"aman@gmail.com");

		// We save the persons
		personService.save(person1);
		personService.save(person2);
		personService.save(person3);
	}

	

}
