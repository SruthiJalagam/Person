package com.java.person.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.person.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    FileWriterService fileWriterService;

    public String processInput(String input) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<Person> personArrayList = objectMapper.readValue(input,
                TypeFactory.defaultInstance().constructCollectionType(List.class, Person.class));

        System.out.println("Person object is " + personArrayList.size());
        fileWriterService.writeFile(personArrayList);

        return "";
    }
}
