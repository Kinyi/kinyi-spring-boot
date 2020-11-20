package com.dataw.rhino.demo;

import lombok.Data;

/**
 * @author Kinyi_Chan
 * @since 2020-11-20
 */
@Data
public class Hobby {

    private String name;

    private Integer price;

    private Integer type;

    public Hobby(String name, Integer price, Integer type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
