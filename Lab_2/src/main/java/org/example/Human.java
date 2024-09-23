package org.example;

class Человек implements Participant {
    private String имя;
    private int maxRunDistance;
    private int maxJumpHeight;
    private static int totalSuperJumps = 3; // Общее количество супер-прыжков для всех людей
    private static int usedSuperJumps = 0; // Количество использованных супер-прыжков

    public Человек(String имя, int maxRunDistance, int maxJumpHeight) {
        this.имя = имя;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public String getName() {
        return имя;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(имя + " успешно пробежал " + distance + " метров.");
            return true;
        } else if (usedSuperJumps < totalSuperJumps) {
            usedSuperJumps++;
            System.out.println(имя + " использовал супер-прыжок и успешно пробежал " + distance + " метров.");
            return true;
        } else {
            System.out.println(имя + " не смог пробежать " + distance + " метров.");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(имя + " успешно прыгнул " + height + " метров.");
            return true;
        } else if (usedSuperJumps < totalSuperJumps) {
            usedSuperJumps++;
            System.out.println(имя + " использовал супер-прыжок и успешно прыгнул " + height + " метров.");
            return true;
        } else {
            System.out.println(имя + " не смог прыгнуть " + height + " метров.");
            return false;
        }
    }
}