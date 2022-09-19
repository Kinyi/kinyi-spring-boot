package com.dataw.rhino.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kinyi_Chan
 * @since 2018-11-23.
 */
@Slf4j
@RestController
//@Api(value = "test controller", tags = "test")
public class HelloController {

    @GetMapping("/")
//    @ApiOperation(value = "index", httpMethod = "GET")
    public String index() {
        String sentence = "Greetings from Spring Boot!";
        log.info(sentence);
        return sentence;
    }

    @PostMapping(value = "/getName")
//    @ApiOperation(value = "getUserName", httpMethod = "POST")
    public String getUserName(@RequestParam(value = "name") String userName) {
        log.info("hi, " + userName);
        return "hi, " + userName;
    }

    @GetMapping("/index2")
//    @ApiOperation(value = "index", httpMethod = "GET")
    public String index2() {
        return "Greetings from Spring Boot to kinyi!";
    }
}
