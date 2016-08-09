package br.com.leandrojacomelli.marsexplorer.explorer.command;

import br.com.leandrojacomelli.marsexplorer.common.command.Command;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.ExplorerSetup;

import static com.google.common.base.Preconditions.checkNotNull;

public class LandExplorerCommand extends Command {

    private final ExplorerId explorerId;
    private final ExplorerSetup setup;


    public LandExplorerCommand(ExplorerSetup setup) {
        this.explorerId = ExplorerId.randomId();
        this.setup = checkNotNull(setup);
    }

    public ExplorerId getExplorerId() {
        return explorerId;
    }

    public ExplorerSetup getSetup() {
        return setup;
    }
}
