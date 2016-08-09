package br.com.leandrojacomelli.marsexplorer.common.infrastructure;


import br.com.leandrojacomelli.marsexplorer.common.GenericId;
import br.com.leandrojacomelli.marsexplorer.common.event.DomainEvent;
import br.com.leandrojacomelli.marsexplorer.common.event.DomainEventStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class InMemoryDomainEventStore<T extends  GenericId>implements DomainEventStore {

    private final List<DomainEvent<T>> events = new ArrayList<>();

    @Override
    public synchronized List<DomainEvent> loadEvents(GenericId id) {
        List<DomainEvent> loadedEvents = events.stream().filter(event -> event.getAggregateId().equals(id)).collect(Collectors.toList());
        if (loadedEvents.isEmpty()) throw new IllegalArgumentException("Aggregate does not exist: " + id);
        else return loadedEvents;
    }


    @Override

    public synchronized void save(GenericId id, List events) {
        this.events.addAll(events);
    }

    @Override
    public synchronized List<DomainEvent> getAllEvents() {
        List<DomainEvent> domainEvents = new ArrayList<>(events);
        Collections.reverse(domainEvents);
        return domainEvents;
    }

}
