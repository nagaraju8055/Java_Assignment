package com.org.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.app.model.Address;
import com.org.app.model.Person;
import com.org.app.repo.AddressRepository;
import com.org.app.repo.PersonRepository;

import io.swagger.annotations.Api;

@Api(value = "address")
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private PersonRepository personrepo;

	/*
	 * @GetMapping public ResponseEntity<List<Address>>
	 * getAllAddresss(@RequestParam(required = false) String title) { try {
	 * List<Address> address = new ArrayList<Address>();
	 * 
	 * if (title == null) addressRepo.findAll().forEach(address::add);
	 * 
	 * if (address.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 * }
	 * 
	 * return new ResponseEntity<>(address, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @GetMapping("/{id}") public ResponseEntity<Address>
	 * getAddressById(@PathVariable("id") long id) { Optional<Address> addressData =
	 * addressRepo.findById(id);
	 * 
	 * if (addressData.isPresent()) { return new ResponseEntity<>(addressData.get(),
	 * HttpStatus.OK); } else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * }
	 */
	@PostMapping("/{PersonId}")
	public ResponseEntity<Address> createAddress(@RequestBody Address address, @PathVariable("PersonId") Long id) {
		try {
			Optional<Person> PersonData = personrepo.findById(id);
			if (PersonData.isPresent()) {
				address.setCity(address.getCity());
				address.setState(address.getState());
				address.setPostalCode(address.getPostalCode());
				address.setStreet(address.getStreet());
				address.setPersonId(PersonData.get().getId());
				Address addressResult = addressRepo.save(address);
				return new ResponseEntity<>(addressResult, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{PersonId}")
	public ResponseEntity<Address> updateAddress(@PathVariable("PersonId") Long id, @RequestBody Address address) {
		Optional<Address> PersonData = addressRepo.findByPersonId(id);
		if (PersonData.isPresent()) {
				Address addressUpdate = new Address();
				addressUpdate.setCity(address.getCity());
				addressUpdate.setState(address.getState());
				addressUpdate.setPostalCode(address.getPostalCode());
				addressUpdate.setStreet(address.getStreet());
				address.setPersonId(PersonData.get().getId());
				return new ResponseEntity<>(addressRepo.save(addressUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") long id) {
		try {
			addressRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
