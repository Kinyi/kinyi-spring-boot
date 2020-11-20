package com.dataw.rhino.controller;

import com.dataw.rhino.bean.PeopleConverter;
import com.dataw.rhino.demo.Hobby;
import com.dataw.rhino.demo.People;
import com.dataw.rhino.demo.PeopleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Kinyi_Chan
 * @since 2020-11-20
 */
@RestController
@RequestMapping
public class BeanCopyController {

    @Autowired
    private PeopleConverter peopleConverter;

    @GetMapping("/test")
    public void testBeanCopy() {
        People people = new People();
        people.setName("allen");
        people.setAge(12);
        people.setAddress("guanghzou");
        people.setBirth(new Date());
        people.setHobbyList(Collections.singletonList(new Hobby("basketball", 100, 0)));

        People people1 = new People();
        people1.setName("allen");
        people1.setAge(12);
        people1.setAddress("guanghzou");
        people1.setBirth(new Date());
        people1.setHobbyList(Collections.singletonList(new Hobby("basketball", 100, 0)));

        People people2 = new People();
        people2.setName("allen");
        people2.setAge(12);
        people2.setAddress("guanghzou");
        people2.setBirth(new Date());
        people2.setHobbyList(Collections.singletonList(new Hobby("basketball", 100, 0)));

        List<People> list = new ArrayList<>();
        list.add(people1);
        list.add(people2);

        PeopleDTO peopleDTO = peopleConverter.domain2dto(people);
        List<PeopleDTO> peopleDTOS = peopleConverter.domain2dto(list);
        System.out.println(peopleDTO);
        System.out.println(peopleDTOS);
    }
}
