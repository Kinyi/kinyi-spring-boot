package com.dataw.rhino.reflect.noimpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Kinyi_Chan
 * @since 2021-01-20
 */
public class AnProxyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("enter proxy handler");
        return "success";
    }
}
