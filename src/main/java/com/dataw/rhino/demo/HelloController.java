package com.dataw.rhino.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/getName", method = RequestMethod.POST)
    public String getUserName(@RequestParam(value = "name") String userName) {
        return "hi, " + userName;
    }
    
}
