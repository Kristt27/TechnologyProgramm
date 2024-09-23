package org.example;

class Стегозавр extends Динозавр {
    public Стегозавр(String имя, double вес) {
        super(имя, вес);
    }

    @Override
    public void run(int distance) {
        System.out.println(имя + " пробежал " + distance + " м, но он слишком большой чтобы плавать");
    }

    @Override
    public void swim(int distance) {
        System.out.println(имя + " не может плавать");
    }
}