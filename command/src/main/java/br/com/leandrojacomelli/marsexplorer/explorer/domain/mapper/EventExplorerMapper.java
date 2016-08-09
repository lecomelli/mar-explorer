package br.com.leandrojacomelli.marsexplorer.explorer.domain.mapper;


import br.com.leandrojacomelli.marsexplorer.explorer.domain.Coordinate;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.Direction;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.ExplorerBoundary;

import static br.com.leandrojacomelli.marsexplorer.explorer.domain.ExplorerBoundary.ExplorerBoundaryBuilder.explorerBoundaryBuilder;


public class EventExplorerMapper {

    public static Coordinate fromEvent(br.com.leandrojacomelli.marsexplorer.explorer.event.Coordinate coordinate) {
        return Coordinate.at(coordinate.getX(), coordinate.getY());
    }

    public static ExplorerBoundary fromEvent(br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerBoundary boundary) {
        return explorerBoundaryBuilder()
                .withLowerBoundary(fromEvent(boundary.getLowerBoundary()))
                .withUpperBoundary(fromEvent(boundary.getUpperBoundary()))
                .build();
    }

    public static Direction fromEvent(br.com.leandrojacomelli.marsexplorer.explorer.event.Direction direction) {
        return Direction.valueOf(direction.name());
    }

    public static br.com.leandrojacomelli.marsexplorer.explorer.event.Coordinate toEvent(Coordinate coordinate) {
        return new br.com.leandrojacomelli.marsexplorer.explorer.event.Coordinate(coordinate.getX(), coordinate.getY());
    }

    public static br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerBoundary toEvent(ExplorerBoundary boundary) {
        return new br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerBoundary(toEvent(boundary.getLowerBoundary()), toEvent(boundary.getUpperBoundary()));
    }

    public static br.com.leandrojacomelli.marsexplorer.explorer.event.Direction toEvent(Direction direction) {
        return br.com.leandrojacomelli.marsexplorer.explorer.event.Direction.valueOf(direction.name());
    }


}
