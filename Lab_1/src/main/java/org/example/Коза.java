package org.example;

class Коза extends ДомашниеЖивотные {
    public Коза(String имя) {
        super(имя);
    }

    @Override
    public void run(int distance) {
        System.out.println(имя + " пробежал " + distance + " м");
    }

    @Override
    public void swim(int distance) {
        System.out.println(имя + " не умеет плавать");
    }

    @Override
    public void voice() {
        System.out.println(имя + " говорит: 'Мее-ее!'");
    }
}
