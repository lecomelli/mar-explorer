package br.com.leandrojacomelli.marsexplorer.explorer.command;


import br.com.leandrojacomelli.marsexplorer.common.domain.Repository;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.Explorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ExplorerCommandHandler {

    private final Repository repository;

    @Autowired
    public ExplorerCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @EventListener()
    public void handle(LandExplorerCommand command) {
        Explorer explorer = new Explorer();
        explorer.land(command.getExplorerId(), command.getSetup());
        repository.save(explorer);
    }

    @EventListener()
    public void handle(MoveExplorerCommand command) {
        Explorer explorer = repository.load(command.getExplorerId(), Explorer.class);
        explorer.move(command.getExplorerId(), command.getMovements());
        repository.save(explorer);
    }

}
