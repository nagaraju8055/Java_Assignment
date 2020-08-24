package com.org.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.app.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	public Optional<Address> findByPersonId(Long personId);
}