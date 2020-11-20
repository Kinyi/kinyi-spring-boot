package com.dataw.rhino.demo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Kinyi_Chan
 * @since 2018-11-29
 */
@Data
public class People {

    private String name;

    private Integer age;

    private String sex;

    private String address;

    private Date birth;

    private List<Hobby> hobbyList;

//    private People() {
//    }

    public void setName(String name) {
        this.name = name;
    }
}
