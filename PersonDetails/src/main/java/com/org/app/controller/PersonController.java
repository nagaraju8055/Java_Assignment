package com.org.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.app.model.Person;
import com.org.app.repo.PersonRepository;

import io.swagger.annotations.Api;

@Api(value = "person")
@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personrepo;

	@GetMapping
	  public ResponseEntity<List<Person>> getAllPersons(@RequestParam(required = false) String title) {
	    try {
	      List<Person> Persons = new ArrayList<Person>();

	      if (title == null)
	        personrepo.findAll().forEach(Persons::add);

	      if (Persons.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(Persons, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
	    Optional<Person> PersonData = personrepo.findById(id);

	    if (PersonData.isPresent()) {
	      return new ResponseEntity<>(PersonData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping
	  public ResponseEntity<Person> createPerson(@RequestBody Person Person) {
	    try {
	      Person _Person = personrepo.save(Person);
	      return new ResponseEntity<>(_Person, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person Person) {
	    Optional<Person> PersonData = personrepo.findById(id);

	    if (PersonData.isPresent()) {
	      Person person = PersonData.get();
	      person.setFirstName(Person.getFirstName());
	      person.setLastName(Person.getLastName());
	      return new ResponseEntity<>(personrepo.save(person), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
	    try {
	    	personrepo.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping
	  public ResponseEntity<HttpStatus> deleteAllPersons() {
	    try {
	    	personrepo.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }
	
}
