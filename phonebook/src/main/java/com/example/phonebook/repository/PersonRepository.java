package com.example.phonebook.repository;

import com.example.phonebook.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
