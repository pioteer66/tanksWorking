package model;

public enum Direction {
    LEFT(0), DOWN(1), RIGHT(2), UP(3);
    private final int value;
    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
