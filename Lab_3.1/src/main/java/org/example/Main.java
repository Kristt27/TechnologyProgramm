package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"191191", "9600", "9009", "100"},
                {"191191", "9600", "9009", "100"},
                //    {"500000", "123222", "50", "300000"},
                {"61000", "1111", "74000", "12000"},
                {"1490", "910000", "0", "666666"}
        };

        try {
            Result result = calculateSum(array);
            System.out.println("Сумма элементов массива: " + result.sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static Result calculateSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException();
            }
        }

        int sum =0;
        List<Exception> otherExceptions = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String value = array[i][j];

                try {
                    int number = Integer.parseInt(value);
                    sum += number;

                    if (isHappyNumber(value)) {
                        otherExceptions.add(new MyHappyNumberException(i, j, value));
                    } else if (isUnluckyNumber(value)) {
                        otherExceptions.add(new UnluckyNumberException(i, j, value));
                    }
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        for (Exception e : otherExceptions) {
            e.printStackTrace();
        }

        return new Result(sum);
    }

    public static boolean isHappyNumber(String number) {
        if (number.length() == 6) {
            int leftSum = sumOfDigits(number.substring(0, 3));
            int rightSum = sumOfDigits(number.substring(3));
            return leftSum == rightSum;
        }
        return false;
    }

    public static boolean isUnluckyNumber(String number) {
        if (number.length() == 6) {
            int leftSum = sumOfDigits(number.substring(0, 3));
            int rightSum = sumOfDigits(number.substring(3));
            return leftSum != rightSum;
        }
        return false;
    }

    private static int sumOfDigits(String digits) {
        return digits.chars().map(Character::getNumericValue).sum();
    }

    // Класс для результата метода calculateSum
    public static class Result {
        public int sum;

        public Result(int sum) {
            this.sum = sum;
        }
    }
}
