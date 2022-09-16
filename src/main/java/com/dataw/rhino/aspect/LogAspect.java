package com.dataw.rhino.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Kinyi_Chan
 * @since 2019-04-15
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before(value = "execution(* com.dataw.rhino.demo.HelloController.*(..))")
    public void log() {
        log.info("invoke controller");
    }

    @Pointcut("within(com.dataw.rhino.serivce.MyServiceImpl)")
    public void specificMethods() {
    }

    @Before("specificMethods()")
    public void cutService() {
        log.info("cut before");
    }

    @After("specificMethods()")
    public void cutAfter() {
        log.info("cut after");
    }
}
