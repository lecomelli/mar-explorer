package br.com.leandrojacomelli.marsexplorer.explorer.domain;

public class Coordinate {

    private final Integer x;
    private final Integer y;

    public Coordinate(final Integer x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate DEFAULT_LOWER_BOUNDARY() {
        return new Coordinate(0, 0);
    }

    public static Coordinate at(final Integer x, final Integer y) {
        return x == null || y == null ? null : new Coordinate(x, y);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Coordinate moveForward(Direction direction) {
        switch (direction) {
            case NORTH:
                return new Coordinate(x, y + 1);
            case SOUTH:
                return new Coordinate(x, y - 1);
            case EAST:
                return new Coordinate(x + 1, y);
            case WEST:
                return new Coordinate(x - 1, y);
            default:
                throw new IllegalStateException("Invalid direction");
        }
    }


}
