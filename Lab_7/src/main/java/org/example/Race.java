package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private ArrayList<Stage> stages;
    private final AtomicInteger finishPosition = new AtomicInteger(0); // Текущее место

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    // Метод для получения списка этапов
    public ArrayList<Stage> getStages() {
        return stages;
    }

    public synchronized int recordFinish(String carName) {
        int position = finishPosition.incrementAndGet(); // Увеличиваем место
        System.out.println(carName + " занял " + position + " место!");
        return position;
    }

}
