package br.com.leandrojacomelli.marsexplorer.common.event;


import br.com.leandrojacomelli.marsexplorer.common.GenericId;

import java.util.List;


public interface DomainEventStore <T extends GenericId> {

    List loadEvents(T id);

    void save(T id, List<DomainEvent<T>>  events);

    List<DomainEvent<T>>  getAllEvents();

}

