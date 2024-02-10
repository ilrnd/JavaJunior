package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Задача 1:
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */
public class Program {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Animal [] animals = {
                new Cat("Barsik", 1, "White"),
                new Dog("Reks", 2, 3)
        };

        for (Animal animal: animals){
            System.out.println(animal.getClass().getSimpleName());
            try {
                Method method = animal.getClass().getMethod("makeSound");
                method.invoke(animal);
            } catch (NoSuchMethodException e) {
                System.out.printf("Animal %s can't make sound", animal.getName());
            }
            try {
                Field colorField = animal.getClass().getDeclaredField("color");
                colorField.setAccessible(true);
                String color = (String) colorField.get(animal);
                System.out.printf("Color: %s \n", color);
            } catch (NoSuchFieldException e){
                System.out.println("Color: no data");
            }
            try {
                Field weightField = animal.getClass().getDeclaredField("weight");
                weightField.setAccessible(true);
                int weight = (int) weightField.get(animal);
                System.out.printf("Weight: %s \n", weight);
            } catch (NoSuchFieldException e){
                System.out.println("Weight: no data");
            }
            System.out.println();
        }
    }
}