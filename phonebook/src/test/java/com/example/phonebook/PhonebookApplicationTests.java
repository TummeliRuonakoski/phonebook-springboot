package com.example.phonebook;

import java.util.List;

import com.example.phonebook.model.Person;
import com.example.phonebook.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PhonebookApplicationTests {


	@Autowired
    private MockMvc mockMvc;

	@Autowired
	private PersonRepository personRepository;


	

	@Test
	public void getAll() throws Exception {
		Person person1 = new Person();
		person1.setName("Heikki Heikki");
		person1.setPhonenumber("+3585409621893");
		personRepository.save(person1);

		Person person2 = new Person();
		person2.setName("Nina Heikki");
		person2.setPhonenumber("+358509821893");
		personRepository.save(person2);

		Person person3 = new Person();
		person3.setName("Viima Nano");
		person3.setPhonenumber("+358509800893");
		personRepository.save(person3);

		List<Person> persons = personRepository.findAll();
		Assertions.assertEquals(3, persons.size(), "findAll should return 3 persons");
    
	}

	@Test
	public void createPerson() throws Exception{
		Person person = new Person();
		person.setName("Jenny Heikki");
		person.setPhonenumber("+358509621893");
		personRepository.save(person);

        Assertions.assertNotNull(person, "The saved person should not be null");
    }


	@Test
	public void GetPerson() throws Exception{
		Person person = new Person();
		person.setName("Jenny Viiva");
		person.setPhonenumber("+358509621893");
		personRepository.save(person);

		personRepository.findById(1L);

        Assertions.assertNotNull(person, "A person not found");

	}

	@Test
	public void updatePerson() throws Exception{
		Person person = new Person();
		person.setName("Lotta Janakka");
		person.setPhonenumber("+358459621893");
		personRepository.save(person);

		String jsonString = "{\n" +
                "\"name\":\"Lotta Janakkala\",\n" +
                "\"phonenumber\":\"+358459621893\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Lotta Janakkala")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phonenumber",Matchers.is("+358459621893")));
			
	}

	@Test
	public void deletePerson() throws Exception{

		Person person = new Person();
		person.setName("Jaana Janakka");
		person.setPhonenumber("+358459601893");
		personRepository.save(person);

		personRepository.deleteById(1L);

	}

}
