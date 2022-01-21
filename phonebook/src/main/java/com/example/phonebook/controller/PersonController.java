package com.example.phonebook.controller;

import java.util.List;

import com.example.phonebook.model.Person;
import com.example.phonebook.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons")
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person getOne(@PathVariable Long id){
        return personRepository.getById(id);
    }

    @PostMapping("/persons")
    public Person postOne(@RequestBody Person person){
        return personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public Person putOne(@RequestBody Person person, @PathVariable Long id){
        return personRepository.findById(id).map(changeData -> {
            changeData.setName(person.getName());
            changeData.setPhonenumber(person.getPhonenumber());
            return personRepository.save(changeData);
        })
        .orElseGet(() -> {
            return personRepository.save(person);
        });
    }

    @DeleteMapping("/persons/{id}")
    public Person deleteOne(@PathVariable Long id){
        Person person = personRepository.getById(id);
        personRepository.delete(person);
        return person;
    }
}
