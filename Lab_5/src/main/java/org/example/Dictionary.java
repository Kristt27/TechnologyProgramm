package org.example;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class Dictionary {
    private final Map<String, Set<String>> translations;

    public Dictionary() {
        translations = new HashMap<>(); // Создаём пустую карту на основе HashMap
    }

    public void add(String word, String translation) {
        if (!translations.containsKey(word)) {
            // Если слова нет, добавляем его с пустым множеством переводов
            translations.put(word, new LinkedHashSet<>()); // Используем LinkedHashSet для сохранения порядка
        }

        translations.get(word).add(translation); // HashSet автоматически исключит дубликаты
    }
    // Добавляем перевод в множество (дубликаты исключаются автоматически)
    public void print() {
        System.out.println(translations.entrySet());
    }
}

