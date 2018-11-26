package com.dataw.rhino.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/getName", method = RequestMethod.POST)
    public String getUserName(@RequestParam(value = "name") String userName) {
        log.info("hi, " + userName);
        return "hi, " + userName;
    }
    
}
