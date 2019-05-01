package com.kairosds.practice.webservice.dao;

import java.util.List;

import com.kairosds.practice.webservice.entity.Person;

/**
 * 
 * This interface define the methods for the CRUD used in PersonServiceImpl
 * 
 * @author jsvruiz
 * 
 */
public interface PersonDAO {
	
	public void saveOrUpdate(Person person);
	
	public List<Person> get();

	public Person get(int id);

	public void delete(int id);

}
