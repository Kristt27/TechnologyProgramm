package org.example;
import java.util.Arrays;

public class Main {
    static final int size = 60_000_000;  // Размер массива
    static final int half = size / 2;    // Для второго метода

    public static void main(String[] args) {
        methodOne();
        methodTwo();
        methodThree(5);  // Запуск с 5 потоками
    }

    // Первый метод: замер времени только на цикл расчета
    public static void methodOne() {
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();  // Замер времени начала работы

        for (int i = 0; i < size; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long endTime = System.currentTimeMillis();  // Замер времени после выполнения цикла

        // Данные первой и последней ячейки массива для проверки
        System.out.println(array[0]);
        System.out.println(array[size - 1]);

        System.out.println("Время выполнения первого метода: " + (endTime - startTime));
    }

    // Второй метод: замер времени на разбивку массива, обработку и склейку
    public static void methodTwo() {
        float[] array = new float[size];
        float[] firstHalf = new float[half];
        float[] secondHalf = new float[half];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();  // Замер времени начала работы

        // Разбиваем массив на две части
        System.arraycopy(array, 0, firstHalf, 0, half);
        System.arraycopy(array, half, secondHalf, 0, half);

        // Обработка первой половины
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < firstHalf.length; i++) {
                firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        threadOne.start();

        // Обработка второй половины
        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < secondHalf.length; i++) {
                secondHalf[i] = (float) (secondHalf[i] * Math.sin(0.2f + (half + i) / 5) * Math.cos(0.2f + (half + i) / 5) * Math.cos(0.4f + (half + i) / 2));
            }
        });
        threadTwo.start();

        // Ожидаю завершения потоков
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Склеиваю результаты обратно в массив
        System.arraycopy(firstHalf, 0, array, 0, half);
        System.arraycopy(secondHalf, 0, array, half, half);

        long endTime = System.currentTimeMillis();  // Замер времени после выполнения обработки

        // вывод данных первой и последней ячейки массива для проверки
        System.out.println(array[0]);
        System.out.println(array[size - 1]);

        System.out.println("Время выполнения второго метода: " + (endTime - startTime));
    }

    // Третий метод: динамическая корректировка количества потоков для равномерного деления
    public static void methodThree(int n) {
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();  // Замер времени начала работы

        // Проверка делимости и корректировка количества потоков
        if (size % n != 0) {
            n++;  // Увеличиваем количество потоков, если не делится нацело
        }

        // Размер части, которая будет обрабатываться каждым потоком
        int partSize = size / n;
        int remainder = size % n;  // Остаток от деления

        Thread[] threads = new Thread[n];
        float[][] results = new float[n][];

        // Разделение массива на части с учетом остатка
        for (int i = 0; i < n; i++) {
            final int startIndex = i * partSize;
            final int endIndex = (i == n - 1) ? (startIndex + partSize + remainder) : (startIndex + partSize);

            final int index = i;  // Локальная копия индекса для лямбда-выражений

            threads[i] = new Thread(() -> {
                float[] subArray = Arrays.copyOfRange(array, startIndex, endIndex);
                for (int j = 0; j < subArray.length; j++) {
                    subArray[j] = (float) (subArray[j] * Math.sin(0.2f + (startIndex + j) / 5) * Math.cos(0.2f + (startIndex + j) / 5) * Math.cos(0.4f + (startIndex + j) / 2));
                }
                results[index] = subArray;  // Сохраняем обработанный результат
            });
            threads[i].start();
        }

        // Ждем завершения всех потоков
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Склеиваем результаты обратно в исходный массив
        for (int i = 0; i < n; i++) {
            System.arraycopy(results[i], 0, array, i * partSize, results[i].length);
        }

        long endTime = System.currentTimeMillis();  // Замер времени после выполнения обработки

        // Выводим данные первой и последней ячейки массива для проверки
        System.out.println(array[0]);
        System.out.println(array[size - 1]);

        System.out.println("Количество потоков: " + n);
        System.out.println("Время выполнения третьего метода: " + (endTime - startTime));
    }
}
