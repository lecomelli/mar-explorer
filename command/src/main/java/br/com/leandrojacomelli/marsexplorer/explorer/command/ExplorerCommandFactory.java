package br.com.leandrojacomelli.marsexplorer.explorer.command;

import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.LandExplorerRequest;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.MoveExplorerRequest;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.leandrojacomelli.marsexplorer.explorer.domain.Coordinate.at;
import static br.com.leandrojacomelli.marsexplorer.explorer.domain.Direction.from;
import static br.com.leandrojacomelli.marsexplorer.explorer.domain.ExplorerBoundary.ExplorerBoundaryBuilder.explorerBoundaryBuilder;
import static br.com.leandrojacomelli.marsexplorer.explorer.domain.ExplorerSetup.ExplorerSetupBuilder.explorerSetupBuilder;

@Service
public class ExplorerCommandFactory {

    public LandExplorerCommand toCommand(LandExplorerRequest request) {

        final Coordinate upperBoundary = at(request.getUpperBoundary().getY(), request.getUpperBoundary().getY());
        final Coordinate lowerBoundary = at(request.getLowerBoundary().getY(), request.getLowerBoundary().getY());

        final ExplorerBoundary boundaryArea = explorerBoundaryBuilder()
                .withUpperBoundary(upperBoundary)
                .withLowerBoundary(lowerBoundary)
                .build();

        final ExplorerSetup setup = explorerSetupBuilder()
                .boundaryArea(boundaryArea)
                .landing(at(request.getLocation().getX(), request.getLocation().getY()))
                .facing(from(request.getDirection()))
                .build();

        return new LandExplorerCommand(setup);
    }

    public MoveExplorerCommand toCommand(MoveExplorerRequest request) {
        List<ExplorerMovement> movements = request
                .getMovements()
                .stream()
                .map(ExplorerMovement::from)
                .collect(Collectors.toList());
        return new MoveExplorerCommand(new ExplorerId(request.getExplorerId()), movements);
    }


}
