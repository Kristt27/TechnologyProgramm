package org.example;

abstract class Животное {
    protected String имя;
    protected static int count = 0; // Счетчик экземпляров

    public Животное(String имя) {
        this.имя = имя;
        count++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);

    public static int getCount() {
        return count;
    }
}