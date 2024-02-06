package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Напишите программу, которая использует Stream API для обработки списка чисел. Программа должна вывести на экран
 * среднее значение всех четных чисел в списке.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 4, 5, 6, 7, 8);

        Double result = list
                .stream()
                .filter(x -> x % 2 == 0)
                .mapToInt(Integer::intValue)
                .average().getAsDouble();
        System.out.println(result);

    }
}