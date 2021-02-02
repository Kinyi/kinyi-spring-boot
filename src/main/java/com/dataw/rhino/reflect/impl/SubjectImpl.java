package com.dataw.rhino.reflect.impl;

/**
 * @author Kinyi_Chan
 * @since 2021-01-20
 */
public class SubjectImpl implements Subject {

    @Override
    public String sayHello() {
        System.out.println("Hello World");
        return "success";
    }
}
