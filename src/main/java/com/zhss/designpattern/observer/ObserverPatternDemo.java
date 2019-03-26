package com.zhss.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 *
 * @author Kinyi_Chan
 * @since 2019-03-26
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject(24);
        subject.addObserver(new ConcreteObserver());
        subject.setName("kinyi");
        subject.setName("shiny");
    }
}

class Subject extends Observable {
    String name;
    Integer age;

    public Subject(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setChanged();
        this.notifyObservers(name);
    }
}

class ConcreteObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Subject subject = (Subject) o;
        System.out.println(String.format("name: %s, age: %s", subject.getName(), subject.getAge()));
    }
}