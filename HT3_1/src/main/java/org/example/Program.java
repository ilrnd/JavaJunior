package org.example;
/*
Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
Обеспечьте поддержку сериализации для этого класса.
Создайте объект класса Student и инициализируйте его данными.
Сериализуйте этот объект в файл.
Десериализуйте объект обратно в программу из файла.
Выведите все поля объекта, включая GPA, и ответьте на вопрос,
почему значение GPA не было сохранено/восстановлено.
2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
 */

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Ivan", 20, 4.8);
        student.displayInfo();
        try (FileOutputStream fileOut = new FileOutputStream("student.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(student);
            System.out.println(String.format("Объект %s сериализован.", Student.class.getSimpleName()));
        }

        try (FileInputStream fileIn = new FileInputStream("student.bin");
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            student = (Student) in.readObject();
            System.out.println(String.format("Объект %s десериализован.", Student.class.getSimpleName()));
        }
        student.displayInfo();
    }
}