package org.example;

abstract class ДомашниеЖивотные extends Животное {
    public ДомашниеЖивотные(String имя) {
        super(имя);
    }

    public abstract void voice();
}