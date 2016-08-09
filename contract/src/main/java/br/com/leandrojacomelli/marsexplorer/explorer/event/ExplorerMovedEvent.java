package br.com.leandrojacomelli.marsexplorer.explorer.event;

import br.com.leandrojacomelli.marsexplorer.common.event.DomainEvent;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;

import java.io.Serializable;

public class ExplorerMovedEvent extends DomainEvent<ExplorerId> implements Serializable {

    private final ExplorerBoundary boundary;
    private final Coordinate coordinate;
    private final Direction direction;

    public ExplorerMovedEvent(final ExplorerId aggregateId,
                              final int version,
                              final long timestamp,
                              final ExplorerBoundary boundary,
                              final Coordinate coordinate,
                              final Direction direction) {
        super(aggregateId, version, timestamp);
        this.boundary = boundary;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public ExplorerBoundary getBoundary() {
        return boundary;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }
}
