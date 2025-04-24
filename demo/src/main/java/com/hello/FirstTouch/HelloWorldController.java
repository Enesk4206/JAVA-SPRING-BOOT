package com.hello.FirstTouch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String Message(){
        return "Hello world from Spring Boot with maven it's edited";
    }
    
}
    