package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Задача 1
        // Массив слов для анализа
        String[] words = {"green", "green", "red", "orange", "blue", "blue"};

        // Преобразование массива в поток, выполнение группировки и подсчета повторений
        String result = Arrays.stream(words)
                // Группировка слов, игнорируя регистр, и подсчет их частоты
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))

                // Преобразование Map в поток записей (ключ-значение)
                .entrySet()
                .stream()

                // Фильтрация: оставляем только те записи, где количество повторений равно максимальному количеству повторений
                .filter(entry -> entry.getValue().intValue() == Arrays.stream(words)
                        // Группировка слов по частоте повторений
                        .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                        .entrySet()
                        .stream()
                        // Извлечение всех значений частоты повторений
                        .map(Map.Entry::getValue)
                        // Нахождение максимальной частоты
                        .max(Long::compare)
                        // Если нет значений, возвращаем 0L
                        .orElse(0L)
                        // Преобразование максимальной частоты в целое число
                        .intValue())

                // Извлечение только ключей (слов) из записей
                .map(Map.Entry::getKey)

                // Сортировка слов в обратном алфавитном порядке
                .sorted((a, b) -> b.compareTo(a))

                // Собираем результат в строку с разделением слов запятой и добавлением префикса и суффикса
                .collect(Collectors.joining(", ", "Самые повторяющиеся слова: ", "!"));

        // Вывод результата на консоль
        System.out.println(result);
    }
}
