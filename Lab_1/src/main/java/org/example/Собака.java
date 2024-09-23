package org.example;

class Собака extends Животное {
    private static final int maxRun = 500;
    private static final int maxSwim = 10;

    public Собака(String имя) {
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
        if (distance <= maxSwim) {
            System.out.println(имя + " проплыл " + distance + " м");
        } else {
            System.out.println(имя + " не может проплыть " + distance + " м");
        }
    }
}