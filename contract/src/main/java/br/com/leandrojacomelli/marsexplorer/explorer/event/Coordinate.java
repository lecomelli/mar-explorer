package br.com.leandrojacomelli.marsexplorer.explorer.event;

import java.io.Serializable;

public class Coordinate implements Serializable {

    private final Integer x;
    private final Integer y;

    public Coordinate(final Integer x,
                      final Integer y) {
        this.x = x;
        this.y = y;

    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
