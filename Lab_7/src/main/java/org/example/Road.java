package org.example;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car car, boolean isLastStage) {
        try {
            System.out.println(car.getName() + " начал этап: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.println(car.getName() + " закончил этап: " + description);

            if (isLastStage) {
                // Если это последний этап, записываем место
                car.getRace().recordFinish(car.getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
