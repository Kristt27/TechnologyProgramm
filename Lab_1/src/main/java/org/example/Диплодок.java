package org.example;

class Диплодок extends Динозавр {
    public Диплодок(String имя, double вес) {
        super(имя, вес);
    }

    @Override
    public void run(int distance) {
        System.out.println(имя + " пробежал " + distance);
    }

    @Override
    public void swim(int distance) {
        System.out.println(имя + " проплыл " + distance + " м");
    }
}
