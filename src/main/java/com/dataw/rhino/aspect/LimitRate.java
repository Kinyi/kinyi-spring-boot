package com.dataw.rhino.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kinyi_Chan
 * @since 2022-09-10
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRate {

    String unit() default "second";

    int amount() default 1;
}
