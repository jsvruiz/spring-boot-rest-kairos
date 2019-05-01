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
import com.kairosds.practice.webservice.service.PersonService;
import com.kairosds.practice.webservice.service.PersonServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadPersonTest {

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
	 * 		TEST VIA ENDPOINT
	 * 
	 * --------------------------
	 * 
	 */
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persons/45")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void getByIdIncorrect() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persons/acbv").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void getByIdOK() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persons/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/*
	 * 
	 * --------------------------
	 * 
	 * 		TEST VIA SERVICE
	 * 
	 * --------------------------
	 * 
	 */
	

	@Test
	public void readAllService() {
		assertEquals(3 /* total of persons by default*/, personService.get().size());
	}
	
	@Test
	public void readByIdService() {
		
		// get all the persons
		List<Person> personList = personService.get();
		
		// get one person
		Person person = personService.get(personList.get(0).getId());
		
		// we check one element of the list with the person obtained
		assertEquals(personList.get(0).getId(), person.getId());
		assertEquals(personList.get(0).getName(), person.getName());
	}
	
	@Test
	public void readByIdServiceWithNull() {
		
		// get one person incorrect
		Person person = personService.get(76);
		
		assertEquals(null, person);
	}

}