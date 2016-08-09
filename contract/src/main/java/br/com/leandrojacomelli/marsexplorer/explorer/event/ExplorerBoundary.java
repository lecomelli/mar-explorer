package br.com.leandrojacomelli.marsexplorer.explorer.event;

import java.io.Serializable;


public class ExplorerBoundary implements Serializable {

    private final Coordinate lowerBoundary;
    private final Coordinate upperBoundary;


    public ExplorerBoundary(final Coordinate lowerBoundary,
                            final Coordinate upperBoundary) {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
    }

    public Coordinate getLowerBoundary() {
        return lowerBoundary;
    }

    public Coordinate getUpperBoundary() {
        return upperBoundary;
    }


}
