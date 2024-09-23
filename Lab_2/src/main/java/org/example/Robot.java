package org.example;

class Робот implements Participant {
    private String имя;
    private int maxRunDistance;
    private int maxJumpHeight;
    private int superJumpsRemaining = 1; // Каждый робот может использовать супер-прыжок один раз

    public Робот(String имя, int maxRunDistance, int maxJumpHeight) {
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
        } else if (superJumpsRemaining > 0) {
            superJumpsRemaining--;
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
        } else if (superJumpsRemaining > 0) {
            superJumpsRemaining--;
            System.out.println(имя + " использовал супер-прыжок и успешно прыгнул " + height + " метров.");
            return true;
        } else {
            System.out.println(имя + " не смог прыгнуть " + height + " метров.");
            return false;
        }
    }
}
