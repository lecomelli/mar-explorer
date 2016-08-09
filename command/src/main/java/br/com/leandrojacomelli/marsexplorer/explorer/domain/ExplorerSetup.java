package br.com.leandrojacomelli.marsexplorer.explorer.domain;

import br.com.leandrojacomelli.marsexplorer.explorer.domain.exception.ExplorerSetupException;

import static com.google.common.base.Preconditions.checkNotNull;

public class ExplorerSetup {

    private final ExplorerBoundary boundary;
    private final Direction direction;
    private final Coordinate coordinate;

    private ExplorerSetup(final ExplorerBoundary boundary, final Direction direction, final Coordinate coordinate) {
        this.boundary = checkNotNull(boundary, "Boundary must be defined");
        this.direction = checkNotNull(direction, "Direction must be defined");
        this.coordinate = checkNotNull(coordinate, "Landing point must be defined");
        if (!boundary.insideBoundary(coordinate)) {
            throw new ExplorerSetupException("Explorer must land inside boundaries");
        }
    }


    ExplorerBoundary getBoundary() {
        return boundary;
    }

    Direction getDirection() {
        return direction;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }

    public static class ExplorerSetupBuilder {

        private ExplorerBoundary boundary;
        private Direction direction;
        private Coordinate coordinate;

        private ExplorerSetupBuilder() {
        }

        public static ExplorerSetupBuilder explorerSetupBuilder() {
            return new ExplorerSetupBuilder();
        }

        public ExplorerSetupBuilder boundaryArea(final ExplorerBoundary boundary) {
            this.boundary = checkNotNull(boundary);
            return this;
        }


        public ExplorerSetupBuilder landing(final Coordinate coordinate) {
            this.coordinate = checkNotNull(coordinate);
            return this;
        }

        public ExplorerSetupBuilder facing(final Direction direction) {
            this.direction = checkNotNull(direction);
            return this;
        }

        public ExplorerSetup build() {
            return new ExplorerSetup(boundary, direction, coordinate);
        }


    }

}
