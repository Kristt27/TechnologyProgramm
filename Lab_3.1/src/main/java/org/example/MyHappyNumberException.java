package org.example;

public class MyHappyNumberException extends Exception {
    public MyHappyNumberException(int row, int col, String value) {
        super("Счастливое число найдено в ячейке [" + row + "][" + col + "]: " + value);
    }

}
