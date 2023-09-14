package com.java.person.controller;

import com.java.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping(value="/person")
    public String person(@RequestBody String input) {
        try {

            System.out.println("Given input string is " + input);
            personService.processInput(input);

        } catch (Exception e) {
            return e.getMessage();
        }
        return "I got a rest call";

    }

}
