package com.dataw.rhino.demo;

import lombok.Data;

import java.util.List;

/**
 * @author Kinyi_Chan
 * @since 2020-11-20
 */
@Data
public class PeopleDTO {

    private String name;

    private Integer age;

    private String homeAddress;

    private String birthday;

    private List<Hobby> hobbyList;
}
