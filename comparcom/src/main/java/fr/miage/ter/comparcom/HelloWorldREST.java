package fr.miage.ter.comparcom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldREST {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello world";
    }

}
