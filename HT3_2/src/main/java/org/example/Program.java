package org.example;
import static org.example.StudentApp.*;

public class Program {
    public static void main(String[] args) {
        Student student = new Student("Petya", 20, 4.5);
        // Сериализация объекта в файлы
        saveDataToFile(FILE_JSON, student);
        saveDataToFile(FILE_XML, student);
        saveDataToFile(FILE_BIN, student);

        System.out.println(String.format("Вывод информации об объекте %s до сериализации", student.getClass().getSimpleName()));
        student.displayInfo();

        Student studentReadedFromFile = loadTasksFromFile(FILE_JSON);
        System.out.println(String.format("\nВывод информации об объекте %s (десереализация из JSON)", student.getClass().getSimpleName()));
        studentReadedFromFile.displayInfo();

        studentReadedFromFile = loadTasksFromFile(FILE_XML);
        System.out.println(String.format("\nВывод информации об объекте %s (десереализация из XML)", student.getClass().getSimpleName()));
        studentReadedFromFile.displayInfo();

        studentReadedFromFile = loadTasksFromFile(FILE_BIN);
        System.out.println(String.format("\nВывод информации об объекте %s (десереализация из BIN)", student.getClass().getSimpleName()));
        studentReadedFromFile.displayInfo();

    }
}