package br.com.leandrojacomelli.marsexplorer.explorer.query;

import br.com.leandrojacomelli.marsexplorer.common.config.JmsConfiguration;
import br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerLandedEvent;
import br.com.leandrojacomelli.marsexplorer.explorer.event.ExplorerMovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

@RabbitListener(queues = JmsConfiguration.name)
public class ExplorerListDenormalizer {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    final private InMemoryExplorerRepository repository;

    @Autowired
    public ExplorerListDenormalizer(final InMemoryExplorerRepository repository) {
        this.repository = repository;
    }


    @RabbitHandler
    public void handle(ExplorerLandedEvent event) {
        logger.info("Received: " + event.toString());
        ExplorerProjection explorerProjection = new ExplorerProjection(event.getAggregateId(), event.getTimestamp(), event.getDirection(), event.getBoundary().getLowerBoundary(), event.getBoundary().getUpperBoundary(), event.getCoordinate());
        repository.save(explorerProjection);
    }

    @RabbitHandler
    public void handle(ExplorerMovedEvent event) {
        logger.info("Received: " + event.toString());
        ExplorerProjection explorerProjection = repository.getById(event.getAggregateId());
        explorerProjection.setCoordinate(event.getCoordinate());
        explorerProjection.setDirection(event.getDirection());
        repository.save(explorerProjection);
    }


}
