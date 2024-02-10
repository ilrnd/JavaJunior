package org.example;

/**
 * Задача 1:
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */
public abstract class Animal {
    private String name;
    private int age;

    public Animal() {
        name = "defaultName";
        age = 0;
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayAnimalInfo(){
        System.out.printf("Name: %s\nAge: %d\n", name, age);
    }

    public String getName() {
        return name;
    }
}
