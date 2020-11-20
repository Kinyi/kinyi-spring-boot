package com.dataw.rhino.demo.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Kinyi_Chan
 * @since 2019-11-14
 */
public class SpecialBeanForEngine implements BeanFactoryPostProcessor, BeanNameAware {

    String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;

//        Engine a = (Engine) beanFactory;
//        GenericBeanDefinition gbd = new GenericBeanDefinition();
//        gbd.setBeanClass(EngineFactory.class);
//        gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
//        gbd.setAutowireCandidate(true);
//        bdr.registerBeanDefinition("engine01-gbd", gbd);
    }

    public static class EngineFactory implements FactoryBean<Engine>, BeanNameAware, InvocationHandler {
        String name;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("here is invoke engine: " + method.getName());
            return null;
        }

        @Override
        public void setBeanName(String name) {
            this.name = name;
        }

        @Override
        public Engine getObject() throws Exception {
            System.out.println("EngineFactory to build Engine01, EngineFactory: " + name);

            Engine proxy = (Engine) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Engine.class}, this);
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return Engine.class;
        }
    }
}
