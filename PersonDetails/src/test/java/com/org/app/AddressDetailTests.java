package com.org.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.app.model.Address;
import com.org.app.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringConfigApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressDetailTests {
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
	public void testCreatePerson() {
		Person employee = new Person();
		employee.setFirstName("admin");
		employee.setLastName("admin");
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl() + "/address", employee,
				Person.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePerson() {
		Long id = (long) 1;
		Address addressUpdate = restTemplate.getForObject(getRootUrl() + "/address/" + id, Address.class);
		addressUpdate.setCity("Hyderabad");
		addressUpdate.setState("AP");
		addressUpdate.setPostalCode("55555");
		addressUpdate.setStreet("chandanagar");
		addressUpdate.setPersonId(id);
		restTemplate.put(getRootUrl() + "/persons/" + id, addressUpdate);
		Address updatedEmployee = restTemplate.getForObject(getRootUrl() + "/address/" + id, Address.class);
		assertNotNull(updatedEmployee);
	}

}