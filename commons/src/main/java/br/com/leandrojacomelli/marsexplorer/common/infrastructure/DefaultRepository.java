package br.com.leandrojacomelli.marsexplorer.common.infrastructure;


import br.com.leandrojacomelli.marsexplorer.common.GenericId;
import br.com.leandrojacomelli.marsexplorer.common.domain.AggregateRoot;
import br.com.leandrojacomelli.marsexplorer.common.domain.Repository;
import br.com.leandrojacomelli.marsexplorer.common.event.DomainEvent;
import br.com.leandrojacomelli.marsexplorer.common.event.DomainEventBus;
import br.com.leandrojacomelli.marsexplorer.common.event.DomainEventStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.lang.String.format;

@org.springframework.stereotype.Repository
public class DefaultRepository implements Repository {


    private final DomainEventBus domainEventBus;

    private final DomainEventStore domainEventStore;

    @Autowired
    public DefaultRepository(DomainEventBus domainEventBus, DomainEventStore domainEventStore) {
        this.domainEventBus = domainEventBus;
        this.domainEventStore = domainEventStore;
    }

    @Override
    public <AR extends AggregateRoot> void save(AR aggregateRoot) {
        if (aggregateRoot.hasUncommittedEvents()) {
            List newEvents = aggregateRoot.getUncommittedEvents();

            domainEventStore.save(aggregateRoot.id(), newEvents);
            domainEventBus.publish(newEvents);
        }
    }

    @Override
    public <AR extends AggregateRoot, ID extends GenericId> AR load(ID id, Class<AR> aggregateType) {
        try {
            AR aggregateRoot = aggregateType.newInstance();
            final List history =  domainEventStore.loadEvents(id);
            aggregateRoot.loadFromHistory(history);
            return aggregateRoot;
        } catch (IllegalArgumentException iae) {
            String message = format("Aggregate of type [%s] does not exist, ID: %s", aggregateType.getSimpleName(), id);
            throw new IllegalArgumentException(message);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}