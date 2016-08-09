package br.com.leandrojacomelli.marsexplorer.explorer.domain;

import br.com.leandrojacomelli.marsexplorer.common.domain.AggregateRoot;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.mapper.EventExplorerMapper;
import br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerLandedEvent;
import br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerMovedEvent;

import java.util.List;


public class Explorer extends AggregateRoot<ExplorerId> {

    private Direction direction;
    private ExplorerBoundary boundary;
    private Coordinate coordinate;

    public void land(ExplorerId explorerId, ExplorerSetup setUp) {
        assertExplorerHasNotBeenLanded();
        applyChange(new ExplorerLandedEvent(explorerId, nextVersion(), now(), EventExplorerMapper.toEvent(setUp.getBoundary()), EventExplorerMapper.toEvent(setUp.getCoordinate()), EventExplorerMapper.toEvent(setUp.getDirection())));
    }


    private void assertExplorerHasNotBeenLanded() {
        if (id() != null) throw new IllegalStateException("Explorer has already been landed");
    }

    public void move(ExplorerId explorerId, List<ExplorerMovement> movements) {
        movements.stream().forEach(movement -> movement.apply(this));
        applyChange(new ExplorerMovedEvent(explorerId, nextVersion(), now(), EventExplorerMapper.toEvent(boundary), EventExplorerMapper.toEvent(coordinate), EventExplorerMapper.toEvent(direction)));
    }

    protected void moveForward() {
        coordinate = coordinate.moveForward(direction);
        if (!boundary.insideBoundary(coordinate)) {
            throw new RuntimeException("Explorer moved outside its boundary");
        }
    }

    protected void turnRight() {
        direction = direction.turnRight();
    }

    protected void turnLeft() {
        direction = direction.turnLeft();
    }


    ///Will be invoked via reflections
    void handleEvent(ExplorerMovedEvent event) {
        this.boundary = EventExplorerMapper.fromEvent(event.getBoundary());
        this.coordinate = EventExplorerMapper.fromEvent(event.getCoordinate());
        this.direction = EventExplorerMapper.fromEvent(event.getDirection());
    }

    void handleEvent(ExplorerLandedEvent event) {
        this.boundary = EventExplorerMapper.fromEvent(event.getBoundary());
        this.coordinate = EventExplorerMapper.fromEvent(event.getCoordinate());
        this.direction = EventExplorerMapper.fromEvent(event.getDirection());
    }


}
