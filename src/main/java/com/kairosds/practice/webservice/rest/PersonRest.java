package com.kairosds.practice.webservice.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairosds.practice.webservice.entity.Person;
import com.kairosds.practice.webservice.exception.PersonNotFoundException;
import com.kairosds.practice.webservice.service.PersonService;


/**
 * 
 * 
 * @author jsvruiz
 * 
 * This class contains the operations CRUD over an entity Person
 *
 */
@RestController
@RequestMapping("persons")
public class PersonRest {

	@Autowired
	PersonService personService;

	@GetMapping("/test")
	public String test() {
		return "hello Kairos";
	}

	/*
	 * ------------------
	 * 
	 * Operations CRUD
	 * 
	 * ------------------
	 */

	/*
	 * 
	 * Method POST for create a person
	 * 
	 */
	@PostMapping
	public Person create(@Valid @RequestBody Person person) {
		personService.save(person);
		return person;
	}

	/*
	 * 
	 * Method GET without parameters for get a list of persons
	 * 
	 */
	@GetMapping
	public List<Person> read() {
		return personService.get();
	}

	/*
	 * 
	 * Method GET with a parameter for get a person by id
	 * 
	 */
	@GetMapping("/{id}")
	public Person read(@PathVariable int id) {
		
		// get the person by id
		Person person = personService.get(id);
		
		// We throw an exception when the person not existing
		if(person == null) {
			throw new PersonNotFoundException(id);
		}
		
		return person;
	}
	
	/*
	 * 
	 * Method PUT with a parameter for update a person by id
	 * 
	 */
	@PutMapping
	public Person update(@Valid @RequestBody Person person) {
		
		// update the person by id
		personService.update(person);
		
		return person;
	}
	
	/*
	 * 
	 * Method DELETE with a parameter for delete a person by id
	 * 
	 */
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		
		// delete the person by id
		personService.delete(id);
		
		return "Deleted customer id - " + id;
		
	}

}
