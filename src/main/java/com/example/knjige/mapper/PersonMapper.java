package com.example.knjige.mapper;

import com.example.knjige.DTO.PersonDTO;
import com.example.knjige.model.Person;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper implements IMapper<Person, PersonDTO>{

    @Override
    public Person toModel(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setPhoneNumber(personDTO.getPhoneNumber());
        person.setBrLicneKarte(personDTO.getBrLicneKarte());
        return person;
    }

    @Override
    public List<Person> toModel(List<PersonDTO> dto) {
        List<Person> personList = new ArrayList<>();
        for(PersonDTO personDTO: dto){
            personList.add(toModel(personDTO));
        }
        return personList;
    }

    @Override
    public PersonDTO toDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setEmail(person.getEmail());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        personDTO.setBrLicneKarte(person.getBrLicneKarte());
        return personDTO;
    }

    @Override
    public List<PersonDTO> toDTO(List<Person> model) {
        List<PersonDTO> personDTOS = new ArrayList<>();
        for(Person person: model){
            personDTOS.add(toDTO(person));
        }
        return personDTOS;
    }
}
