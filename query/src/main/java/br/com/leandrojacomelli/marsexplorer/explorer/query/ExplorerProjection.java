package br.com.leandrojacomelli.marsexplorer.explorer.query;

import br.com.leandrojacomelli.marsexplorer.common.query.Projection;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.event.Coordinate;
import br.com.leandrojacomelli.marsexplorer.explorer.event.Direction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExplorerProjection extends Projection {
    private final ExplorerId explorerId;
    private final long explorerLandedTimestamp;

    private final Coordinate lowerBoundary;
    private final Coordinate upperBoundary;

    private Direction direction;
    private Coordinate coordinate;

    public ExplorerProjection(@JsonProperty("explorerId") ExplorerId explorerId,
                              @JsonProperty("explorerLandedTimestamp") long explorerLandedTimestamp,
                              @JsonProperty("direction") Direction direction,
                              @JsonProperty("lowerBoundary") Coordinate lowerBoundary,
                              @JsonProperty("upperBoundary") Coordinate upperBoundary,
                              @JsonProperty("coordinate") Coordinate coordinate) {
        this.explorerId = explorerId;
        this.explorerLandedTimestamp = explorerLandedTimestamp;
        this.direction = direction;
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.coordinate = coordinate;
    }

    public ExplorerId getExplorerId() {
        return explorerId;
    }

    public long getExplorerLandedTimestamp() {
        return explorerLandedTimestamp;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinate getLowerBoundary() {
        return lowerBoundary;
    }

    public Coordinate getUpperBoundary() {
        return upperBoundary;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}


