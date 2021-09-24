package com.dataw.rhino.interfaces;

/**
 * @author Kinyi_Chan
 * @since 2021-04-13
 */
public interface DemoInterface {

    void eat();

    default String say() {
        return "hello";
    }
}
