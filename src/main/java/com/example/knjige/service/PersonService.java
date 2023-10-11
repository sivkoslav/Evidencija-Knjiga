package com.example.knjige.service;

import com.example.knjige.DTO.PersonDTO;
import com.example.knjige.exception.ResourceNotFoundException;
import com.example.knjige.mapper.PersonMapper;
import com.example.knjige.model.Person;
import com.example.knjige.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;



    public List<PersonDTO> getAllPersons(){

        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOS = personMapper.toDTO(personList);
        return personDTOS;
    }

    public Person addNew(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);
        return personRepository.save(person);


    }

    public PersonDTO getPerson(Long id){
        Person person= personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Person with the following id:" + id +" does not exist"));
        PersonDTO personDTO = personMapper.toDTO(person);
        return personDTO;
    }

    public Person updatePerson(PersonDTO personDTO, Long id){
        return personRepository.findById(id).map(person ->{
            person.setFirstName(personDTO.getFirstName());
            person.setLastName(personDTO.getLastName());
            person.setEmail(personDTO.getEmail());
            person.setPhoneNumber(personDTO.getPhoneNumber());
            person.setBrLicneKarte(personDTO.getBrLicneKarte());
            return personRepository.save(person);
        }).orElseThrow(()->new ResourceNotFoundException("User with the id: " + id + " could not be found"));
    }
}
