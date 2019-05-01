package com.kairosds.practice.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kairosds.practice.webservice.dao.PersonDAO;
import com.kairosds.practice.webservice.entity.Person;

/**
 * 
 * This class implements the interface PersonService, all the methods are accessed for the CRUD used in PersonRest
 * 
 * @author jsvruiz
 * 
 */

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonDAO personDAO;
	
	@Override
	@Transactional
	public void save(Person person) {
		personDAO.saveOrUpdate(person);
	}

	@Override
	@Transactional
	public List<Person> get() {
		return personDAO.get();
	}

	@Override
	@Transactional
	public Person get(int id) {
		return personDAO.get(id);
	}
	
	@Override
	@Transactional()
	public void update(Person person) {
		personDAO.saveOrUpdate(person);
	}

	@Override
	@Transactional()
	public void delete(int id) {
		personDAO.delete(id);
	}

}
