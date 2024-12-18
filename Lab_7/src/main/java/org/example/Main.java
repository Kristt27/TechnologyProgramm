package org.example;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static final int CARS_COUNT = 7;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        CountDownLatch readyLatch = new CountDownLatch(CARS_COUNT);
        CountDownLatch finishLatch = new CountDownLatch(CARS_COUNT);

        Car.readyLatch = readyLatch;
        Car.finishLatch = finishLatch;

        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race, 10 + (int) (Math.random() * 30));
            new Thread(cars[i]).start();
        }

        readyLatch.await(); // Ожидание подготовки всех машин
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        finishLatch.await(); // Ожидание завершения всех машин
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
