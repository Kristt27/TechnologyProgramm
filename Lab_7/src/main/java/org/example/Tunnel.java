package org.example;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static final int MAX_CARS = 2; // Максимум машин в тоннеле
    private static final Semaphore semaphore = new Semaphore(MAX_CARS);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car car, boolean isLastStage) {
        try {
            System.out.println(car.getName() + " готовится к этапу (ждет): " + description);
            semaphore.acquire(); // Въезд в тоннель
            System.out.println(car.getName() + " начал этап: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.println(car.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Освобождение места в тоннеле
        }
    }
}
