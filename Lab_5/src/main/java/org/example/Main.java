package org.example;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Задание 1
        String[] words = {"apple", "banana", "orange", "apple", "pear", "banana", "kiwi", "orange", "grape", "kiwi"};

        HashMap<String, Integer> listWord = new HashMap<>();
        for (String s : words) {
            listWord.put(s, listWord.getOrDefault(s, 0) + 1);
        }

        System.out.println("Список слов и их количества:");
        System.out.println(listWord);

        // Справочник-переводчик
        Dictionary dictionary = new Dictionary();
        dictionary.add("apple", "manzana");
        dictionary.add("apple", "яблоко");
        dictionary.add("orange", "пайнэпл");
        dictionary.add("banana", "банан");
        dictionary.add("orange", "апельсин");
        dictionary.add("cherry", "вишня");
        dictionary.add("orange", "naranja");
        dictionary.add("ananas", "пайнэпл");
        dictionary.add("ananas", "ананас");
        dictionary.add("ananas", "пайнэпл");
        System.out.println("\nСправочник переводов:");
        dictionary.print();
    }
}
