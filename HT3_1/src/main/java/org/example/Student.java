package org.example;

import java.io.Serializable;
import java.net.ServerSocket;
import java.text.Format;

public class Student implements Serializable {
    private String name;
    private int age;
    transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }

    public void displayInfo(){
        System.out.println(String.format("Имя: %s\nВозраст: %d\nСредний балл: %.2f", name, age, GPA));
    }
}
