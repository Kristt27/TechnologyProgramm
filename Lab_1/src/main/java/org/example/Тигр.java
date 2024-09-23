package org.example;

class Тигр extends Животное {
    private static final int maxRun = 600;

    public Тигр(String имя) {
        super(имя);
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRun) {
            System.out.println(имя + " пробежал " + distance + " м");
        } else {
            System.out.println(имя + " не может пробежать " + distance + " м");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(имя + " проплыл " + distance + " м");
    }
}
