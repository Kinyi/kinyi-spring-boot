package com.dataw.rhino.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * @author Kinyi_Chan
 * @since 2018-11-26
 */

@Slf4j
public class FirstApplicationTest {

    @Test
    public void test() {
        System.out.println("hello world");
    }

    @Test
    public void testShiro() {
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            log.error(e.getMessage(), e);
        }
        Assert.assertTrue(subject.isAuthenticated());//断言用户已经登录
        //6、退出
        subject.logout();
    }

    @Test
    public void testLambda() {
        System.out.println();
    }

    private void filter(List<String> lang, Predicate<String> condition) {
        lang.stream().filter(condition).forEach(System.out::println);
    }
}