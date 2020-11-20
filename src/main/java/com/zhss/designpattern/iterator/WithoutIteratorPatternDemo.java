package com.zhss.designpattern.iterator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kinyi_Chan
 * @since 2019-03-30
 */
public class WithoutIteratorPatternDemo {

    public static void main(String[] args) {
        Student a = new Student("a");
        new RuntimeException().getStackTrace();
        Student b = new Student("b");
        Map<String, Student> map = new HashMap<>();
        map.put(a.getName(), a);
        map.put(b.getName(), b);
        Classroom classroom = new Classroom(map);

        for (Map.Entry<String, Student> entry : classroom.getStudents().entrySet()) {
            System.out.println(entry);
        }
        for (Student student : classroom.getStudents().values()) {
            System.out.println(student);
        }
    }

    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Classroom {
        Map<String, Student> students;

        public Classroom(Map<String, Student> students) {
            this.students = students;
        }

//        List<Student> students;
//
//        public Classroom(List<Student> students) {
//            this.students = students;
//        }


        public Map<String, Student> getStudents() {
            return students;
        }
    }
}
