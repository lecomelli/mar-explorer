package br.com.leandrojacomelli.marsexplorer.explorer.command;

import br.com.leandrojacomelli.marsexplorer.common.command.Command;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.ExplorerMovement;

import java.util.List;

public class MoveExplorerCommand extends Command {
    private final ExplorerId explorerId;
    private final List<ExplorerMovement> movements;


    public MoveExplorerCommand(ExplorerId explorerId, List<ExplorerMovement> movements) {
        this.explorerId = explorerId;
        this.movements = movements;
    }

    public ExplorerId getExplorerId() {
        return explorerId;
    }

    public List<ExplorerMovement> getMovements() {
        return movements;
    }
}