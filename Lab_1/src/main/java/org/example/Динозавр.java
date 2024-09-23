package org.example;

abstract class Динозавр extends Животное {
    protected double вес;

    public Динозавр(String имя, double вес) {
        super(имя);
        this.вес = вес;
    }

    public void weight() {
        System.out.println(имя + " весит " + вес + " кг");
    }
}