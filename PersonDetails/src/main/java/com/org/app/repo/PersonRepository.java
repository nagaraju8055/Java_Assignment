package com.org.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.app.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
