package br.com.leandrojacomelli.marsexplorer.explorer.query;

import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Long.valueOf;

@Repository
public class InMemoryExplorerRepository {

    private final Map<ExplorerId, ExplorerProjection> explores = new HashMap<>();

    void save(ExplorerProjection explorerProjection) {
        explores.put(explorerProjection.getExplorerId(), explorerProjection);
    }

    ExplorerProjection getById(ExplorerId explorerId) {
        return explores.get(explorerId);
    }

    List<ExplorerProjection> listExplorersByTimestamp() {
        return explores
                .values()
                .stream()
                .sorted((e1, e2) -> valueOf(e1.getExplorerLandedTimestamp()).compareTo(e2.getExplorerLandedTimestamp()))
                .collect(Collectors.toList());
    }
}
