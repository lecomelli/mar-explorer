package br.com.leandrojacomelli.marsexplorer.common.domain;


import br.com.leandrojacomelli.marsexplorer.common.GenericId;

public interface Repository {

    <AR extends AggregateRoot> void save(AR aggregateRoot);

    <AR extends AggregateRoot, ID extends GenericId> AR load(ID id, Class<AR> aggregateType);
}
