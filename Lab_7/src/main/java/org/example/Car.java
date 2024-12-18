package org.example;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public static CountDownLatch readyLatch;
    public static CountDownLatch finishLatch;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    // Добавляем метод для получения объекта Race
    public Race getRace() {
        return race;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            readyLatch.countDown(); // Уведомляем о готовности

            readyLatch.await(); // Ждем старта

            // Проходим этапы гонки
            for (int i = 0; i < race.getStages().size(); i++) {
                boolean isLastStage = (i == race.getStages().size() - 1);
                race.getStages().get(i).go(this, isLastStage); // Передаем флаг последнего этапа
            }

            finishLatch.countDown(); // Уведомляем о завершении
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

