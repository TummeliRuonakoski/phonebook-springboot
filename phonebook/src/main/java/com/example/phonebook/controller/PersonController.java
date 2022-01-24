package com.example.phonebook.controller;

import java.util.List;
import java.util.Optional;

import com.example.phonebook.model.Person;
import com.example.phonebook.repository.PersonRepository;
import com.example.phonebook.NotFoundException;

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
	public Person findById(@PathVariable Long id) {
        Optional <Person> person = personRepository.findById(id);
        if(person.isEmpty()){
            throw new NotFoundException("Person not found");
        }
        return person.get();
	}

    @PostMapping("/persons")
    public Person postOne(@RequestBody Person person) throws Exception{
        if(person.getName().length() < 4 || person.getName().length() > 64){
            throw new Exception("The size of the name must be 4 - 64");
        }
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
    public void deleteOne(@PathVariable Long id){
        personRepository.deleteById(id);
    }
}
