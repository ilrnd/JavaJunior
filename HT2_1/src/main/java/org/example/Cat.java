package org.example;

public class Cat extends Animal{
    private String color;

    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }
    public void makeSound(){
        System.out.println("Sound: Meow");
    }

    public String getColor() {
        return color;
    }
}
