package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        // Задача 2
        // Создание списка продуктов с их наименованиями, ценами, количеством и страной-производителем
        List<Product> products = List.of(
                new Product("Печенье", 150.0, 50, "Country1"),
                new Product("Хлеб", 30.0, 30, "Country2"),
                new Product("Колбаса", 250.0, 20, "Country3"),
                new Product("Икра", 500.0, 10, "Country4"),
                new Product("Молоко", 50.0, 25, "Country4")
        );

        // Переменная N указывает количество самых дорогих продуктов, которые нужно вывести
        int N = 2;

        // Обработка потока продуктов с использованием Stream API
        String result = products.stream()
                // Сортировка сначала по цене в убывающем порядке, затем по количеству в возрастающем порядке
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed() // Сортировка по цене (от дорогих к дешевым)
                        .thenComparingInt(Product::getQuantity)) // Если цена одинаковая, сортировка по количеству (по возрастанию)

                .limit(N)  // Ограничение на первые N продуктов после сортировки по цене
                // Сортируем отобранные N продуктов по количеству в порядке возрастания
                .sorted(Comparator.comparingInt(Product::getQuantity))  // Сортировка по количеству (по возрастанию)

                // Преобразуем поток продуктов в поток их наименований
                .map(Product::getName)

                // Собираем результат в строку с разделением наименований запятой
                .collect(Collectors.joining(", ", N + " самых дорогих продуктов на складе: ", "."));

        // Выводим результат на консоль
        System.out.println(result);
    }
}
