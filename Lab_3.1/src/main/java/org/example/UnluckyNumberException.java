package org.example;

public class UnluckyNumberException extends Exception {
    public UnluckyNumberException(int row, int col, String value) {
        super("Несчастливое число найдено в ячейке [" + row + "][" + col + "]: " + value);
    }

}
