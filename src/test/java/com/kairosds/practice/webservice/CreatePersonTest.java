package com.kairosds.practice.webservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.DateTimeException;
import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

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
public class CreatePersonTest {

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
	public void checkDateIncorrectEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez\",\n" + 
        		"        \"gender\": \"M\",\n" + 
        		"        \"birthdate\": \"1981-12-34\",\n" + 
        		"        \"nationality\": \"Mexican\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87\",\n" + 
        		"        \"email\": \"aman@gmail.com\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(400))
		.andExpect(jsonPath("$.message").value("Remember that the birthdate field valid is: yyyy-mm-dd"))
		.andDo(print());
	}
	
	@Test
	public void checkNameIncorrectEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez2\",\n" + 
        		"        \"gender\": \"M\",\n" + 
        		"        \"birthdate\": \"1981-12-05\",\n" + 
        		"        \"nationality\": \"Mexican\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87\",\n" + 
        		"        \"email\": \"aman@gmail.com\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(400))
		.andExpect(jsonPath("$.message").value("You need send a Person valid object: [the name field is incorrect]"))
		.andDo(print());
	}
	
	@Test
	public void checkGenderIncorrectEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez\",\n" + 
        		"        \"gender\": \"Male\",\n" + 
        		"        \"birthdate\": \"1981-12-05\",\n" + 
        		"        \"nationality\": \"Mexican\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87\",\n" + 
        		"        \"email\": \"aman@gmail.com\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(400))
		.andExpect(jsonPath("$.message").value("You need send a Person valid object: [the gender field must be M or F]"))
		.andDo(print());
	}
	
	@Test
	public void checkNationalityIncorrectEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez\",\n" + 
        		"        \"gender\": \"M\",\n" + 
        		"        \"birthdate\": \"1981-12-05\",\n" + 
        		"        \"nationality\": \"Mexican2222\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87\",\n" + 
        		"        \"email\": \"aman@gmail.com\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(400))
		.andExpect(jsonPath("$.message").value("You need send a Person valid object: [the nationality is incorrect]"))
		.andDo(print());
	}
	
	@Test
	public void checkPhoneIncorrectEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez\",\n" + 
        		"        \"gender\": \"M\",\n" + 
        		"        \"birthdate\": \"1981-12-05\",\n" + 
        		"        \"nationality\": \"Mexican\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87abc\",\n" + 
        		"        \"email\": \"aman@gmail.com\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(400))
		.andExpect(jsonPath("$.message").value("You need send a Person valid object: [Please enter a valid phone number: XXX-XXX-XX-XX]"))
		.andDo(print());
	}
	
	@Test
	public void checkMailIncorrectEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez\",\n" + 
        		"        \"gender\": \"M\",\n" + 
        		"        \"birthdate\": \"1981-12-05\",\n" + 
        		"        \"nationality\": \"Mexican\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87\",\n" + 
        		"        \"email\": \"aman\"\n" + 
        		"    }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(400))
		.andExpect(jsonPath("$.message").value("You need send a Person valid object: [the email is incorrect]"))
		.andDo(print());
	}
	
	@Test
	public void createPersonEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/persons/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" + 
        		"        \"name\": \"Pedro Diaz Hernandez\",\n" + 
        		"        \"gender\": \"M\",\n" + 
        		"        \"birthdate\": \"1981-12-02\",\n" + 
        		"        \"nationality\": \"Mexican\",\n" + 
        		"        \"phoneNumber\": \"552-234-31-87\",\n" + 
        		"        \"email\": \"aman@gmail.com\"\n" + 
        		"    }")
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
	@Test(expected = ConstraintViolationException.class)
	public void checkNameNull() {

		// new person
		Person person = new Person(null/* a name valid */, "F", LocalDate.of(1981, 12, 6), "Mexican", "234-234-23-23",
				"mariag@gmail.com");

		// save a person
		personService.save(person);

	}

	@Test(expected = ConstraintViolationException.class)
	public void checkNameInvalid() {

		// new person
		Person person = new Person("21234"/* a name invalid */, "F", LocalDate.of(1981, 12, 6), "Mexican",
				"234-234-23-23", "mariag@gmail.com");

		// save a person
		personService.save(person);

	}

	@Test(expected = ConstraintViolationException.class)
	public void checkGenderNull() {

		// new person
		Person person = new Person("Jesus Ruiz", null /* a gender invalid */, LocalDate.of(1981, 12, 6), "Mexican",
				"234-234-23-23", "mariag@gmail.com");

		// save a person
		personService.save(person);

	}

	@Test(expected = ConstraintViolationException.class)
	public void checkGenderInvalid() {

		// new person
		Person person = new Person("Jesus Ruiz", "5" /* a gender invalid */, LocalDate.of(1981, 12, 6), "Mexican",
				"234-234-23-23", "mariag@gmail.com");

		// save a person
		personService.save(person);

	}

	@Test(expected = ConstraintViolationException.class)
	public void checkBithdayNull() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", null/* a null in birthday */, "Mexican", "234-234-23-23", "mariag@gmail.com");

		// save a person
		personService.save(person);

	}

	@Test(expected = DateTimeException.class)
	public void checkBithdayInvalid() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 32)/* a birthday incorrect */, "Mexican", "234-234-23-23",
				"mariag@gmail.com");

		// save a person
		personService.save(person);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void checkNationalityNull() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 02), null/* a null in nationality */, "234-234-23-23",
				"mariag@gmail.com");

		// save a person
		personService.save(person);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void checkNationalityInvalid() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 02), "Mexican2"/* a nationality incorrect */, "234-234-23-23",
				"mariag@gmail.com");

		// save a person
		personService.save(person);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void checkPhoneNull() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 02), "Mexican", null/* a null in phone */,
				"mariag@gmail.com");

		// save a person
		personService.save(person);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void checkPhoneInvalid() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 02), "Mexican", "234-234-23-23abc" /* a phone incorrect */,
				"mariag@gmail.com");

		// save a person
		personService.save(person);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void checkEmailNull() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 02), "Mexican", "234-234-23-23",
				null /* email null*/);

		// save a person
		personService.save(person);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void checkEmailInvalid() {

		// new person
		Person person = new Person("Jesus Ruiz", "M", LocalDate.of(1981, 12, 02), "Mexican", "234-234-23-23",
				"maria" /* an email incorrect */);

		// save a person
		personService.save(person);

	}

	@Test
	public void createPersonTest() {

		// total of persons
		int count = personService.get().size();

		// new person
		Person person = new Person("Maria Garcia", "F", LocalDate.of(1981, 12, 6), "Mexican", "234-234-23-23",
				"mariag@gmail.com");

		// save a person
		personService.save(person);

		assertEquals(count + 1, personService.get().size());
	}

}
