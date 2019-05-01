package com.kairosds.practice.webservice.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kairosds.practice.webservice.entity.Person;
import com.kairosds.practice.webservice.exception.PersonNotFoundException;

/**
 * 
 * This class define the methods for the CRUD used in PersonServiceImpl
 * 
 * @author jsvruiz
 * 
 */

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Person> get() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<Person> theQuery = currentSession.createQuery("from Person", Person.class);

		// execute query and get result list
		List<Person> persons = theQuery.getResultList();

		// return the results
		return persons;

	}

	@Override
	public Person get(int id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get and return the person by id
		return currentSession.get(Person.class, id);

	}

	@Override
	public void saveOrUpdate(Person person) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save or update a person object
		currentSession.saveOrUpdate(person);
	}

	@Override
	public void delete(int id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get a person by id
		Person person = currentSession.get(Person.class, id);
		
		// if the person is null, then we throw an exception because there isn't a person with that id
		if(person == null) {
			throw new PersonNotFoundException(id);
		}
		
		// delete the person
		currentSession.delete(person);

	}

}
