package org.example;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car car, boolean isLastStage);
}

