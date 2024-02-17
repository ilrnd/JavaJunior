package org.example;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private double gpa;

    public Student(){

    }

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public void displayInfo(){
        System.out.println(String.format("Имя: %s\nВозраст: %d\nСредний балл: %.2f", name, age, gpa));
    }
}
