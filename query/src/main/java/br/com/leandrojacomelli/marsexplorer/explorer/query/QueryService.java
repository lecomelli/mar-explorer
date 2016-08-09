package br.com.leandrojacomelli.marsexplorer.explorer.query;

import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    @Autowired
    private InMemoryExplorerRepository explorerRepository;

    public List<ExplorerProjection> getExplores() {
        return explorerRepository.listExplorersByTimestamp();
    }

    public ExplorerProjection getExplorer(ExplorerId explorerId) {
        return explorerRepository.getById(explorerId);
    }

}
