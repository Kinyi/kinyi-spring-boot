package com.dataw.rhino.impl;

import com.dataw.rhino.interfaces.DemoInterface;

/**
 * @author Kinyi_Chan
 * @since 2021-04-13
 */
public class DemoImpl implements DemoInterface {

    @Override
    public void eat() {
        System.out.println("I have eat!");
    }

    @Override
    public String say() {
        return DemoInterface.super.say();
    }
}
