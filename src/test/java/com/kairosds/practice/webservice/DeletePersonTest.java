package com.kairosds.practice.webservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kairosds.practice.webservice.dao.PersonDAO;
import com.kairosds.practice.webservice.entity.Person;
import com.kairosds.practice.webservice.exception.PersonNotFoundException;
import com.kairosds.practice.webservice.service.PersonService;
import com.kairosds.practice.webservice.service.PersonServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeletePersonTest {

	@InjectMocks
	PersonServiceImpl PersonServiceMock;

	@Mock
	PersonDAO personDAO;

	@Autowired
	PersonService personService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	/*
	 * 
	 * --------------------------
	 * 
	 * TEST VIA ENDPOINT
	 * 
	 * --------------------------
	 * 
	 */

	@Test
	public void deleteByIdNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/persons/45").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void deleteByIdIncorrect() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/persons/abc").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void deleteByIdOK() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/persons/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/*
	 * 
	 * --------------------------
	 * 
	 * TEST VIA SERVICE
	 * 
	 * --------------------------
	 * 
	 */

	@Test
	public void deleteByIdService() {

		// get all the persons
		List<Person> personList = personService.get();

		// get one person
		personService.delete(personList.get(0).getId());

		// we check one element of the list with the person obtained
		assertEquals(personList.size() - 1, personService.get().size());
	}

	@Test(expected=PersonNotFoundException.class)
	public void deleteByIdIncorrectService() {

		// get one person incorrect
		personService.delete(76);
	}

}
