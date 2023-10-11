package com.example.knjige.controller;

import com.example.knjige.DTO.PersonDTO;
import com.example.knjige.model.Person;
import com.example.knjige.repository.PersonRepository;
import com.example.knjige.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PersonController {

    @Autowired
    private PersonService personService;

    //get all employees
    @GetMapping("/persons")
     ResponseEntity<?> getAllPersons(){
        List<PersonDTO> personDTOList = personService.getAllPersons();
        if(!personDTOList.isEmpty()) {

            return new ResponseEntity<>(personDTOList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addPerson")
    ResponseEntity<?> savePerson(@RequestBody PersonDTO personDTO){

        Person person = personService.addNew(personDTO);

        if(person!=null){
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/person/{id}")
    ResponseEntity<?> getPerson(@PathVariable Long id){
        PersonDTO personDTO = personService.getPerson(id);

        if(personDTO!=null){
            return new ResponseEntity<>(personDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/person/{id}")
    ResponseEntity<?> updatePerson(@RequestBody PersonDTO personDTO, @PathVariable Long id){
        Person person = personService.updatePerson(personDTO, id);

        if(person!=null){
            return new ResponseEntity<>(person, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
