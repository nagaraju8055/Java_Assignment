package com.org.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.org.app.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringConfigApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonDetailsApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllPersons() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/persons", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetPersonById() {
		Person person = restTemplate.getForObject(getRootUrl() + "/persons/1", Person.class);
		System.out.println(person.getFirstName());
		assertNotNull(person);
	}

	@Test
	public void testCreatePerson() {
		Person employee = new Person();
		employee.setFirstName("admin");
		employee.setLastName("admin");
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl() + "/persons", employee,
				Person.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePerson() {
		int id = 1;
		Person person = restTemplate.getForObject(getRootUrl() + "/persons/" + id, Person.class);
		person.setFirstName("admin1");
		person.setLastName("admin2");
		restTemplate.put(getRootUrl() + "/persons/" + id, person);
		Person updatedEmployee = restTemplate.getForObject(getRootUrl() + "/persons/" + id, Person.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeletePerson() {
		int id = 2;
		Person person = restTemplate.getForObject(getRootUrl() + "/persons/" + id, Person.class);
		assertNotNull(person);
		restTemplate.delete(getRootUrl() + "/persons/" + id);
		try {
			person = restTemplate.getForObject(getRootUrl() + "/persons/" + id, Person.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}