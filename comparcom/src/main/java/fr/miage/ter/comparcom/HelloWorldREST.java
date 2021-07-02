package fr.miage.ter.comparcom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class HelloWorldREST {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello world";
    }

}
