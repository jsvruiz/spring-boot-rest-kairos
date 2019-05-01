package com.kairosds.practice.webservice.service;

import java.util.List;

import com.kairosds.practice.webservice.entity.Person;

/**
 * 
 * This interface define the methods for the CRUD used in PersonRest
 * 
 * @author jsvruiz
 * 
 */
public interface PersonService {
	
	public void save(Person person);
	
	public List<Person> get();
	
	public Person get(int id);
	
	public void update(Person person);

	public void delete(int id);

}
