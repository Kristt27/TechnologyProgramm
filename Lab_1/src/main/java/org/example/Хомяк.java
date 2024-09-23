package org.example;

class Хомяк extends ДомашниеЖивотные {
    public Хомяк(String имя) {
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
        System.out.println(имя + " шепчет: 'Пип-пип!'");
    }
}
