package br.com.leandrojacomelli.marsexplorer.explorer.domain;

import static br.com.leandrojacomelli.marsexplorer.explorer.domain.Coordinate.DEFAULT_LOWER_BOUNDARY;
import static com.google.common.base.Preconditions.checkNotNull;

public class ExplorerBoundary {

    private final Coordinate lowerBoundary;
    private final Coordinate upperBoundary;

    private ExplorerBoundary(Coordinate lowerBoundary, Coordinate upperBoundary) {
        this.lowerBoundary = checkNotNull(lowerBoundary);
        this.upperBoundary = checkNotNull(upperBoundary);
    }

    Boolean insideBoundary(Coordinate coordinate) {
        return lowerBoundary.getX() <= coordinate.getX() &&
                lowerBoundary.getY() <= coordinate.getY() &&
                upperBoundary.getX() >= coordinate.getX() &&
                upperBoundary.getY() >= coordinate.getY();
    }

    public Coordinate getLowerBoundary() {
        return lowerBoundary;
    }

    public Coordinate getUpperBoundary() {
        return upperBoundary;
    }

    public static class ExplorerBoundaryBuilder {

        private Coordinate lowerBoundary;
        private Coordinate upperBoundary;

        private ExplorerBoundaryBuilder() {
            lowerBoundary = DEFAULT_LOWER_BOUNDARY();
        }

        public static ExplorerBoundaryBuilder explorerBoundaryBuilder() {
            return new ExplorerBoundaryBuilder();
        }

        public ExplorerBoundaryBuilder withUpperBoundary(Coordinate coordinate) {
            this.upperBoundary = checkNotNull(coordinate);
            return this;
        }

        public ExplorerBoundaryBuilder withLowerBoundary(Coordinate coordinate) {
            this.lowerBoundary = checkNotNull(coordinate);
            return this;
        }


        public ExplorerBoundary build() {
            return new ExplorerBoundary(this.lowerBoundary, this.upperBoundary);
        }

    }


}
