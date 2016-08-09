package br.com.leandrojacomelli.marsexplorer.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DomainEventBus {

    private final Logger log = LoggerFactory.getLogger(DomainEventBus.class);


    private final RabbitTemplate template;

    private final Queue queue;


    @Autowired
    public DomainEventBus(final RabbitTemplate template, final Queue queue) {
        this.queue = queue;
        this.template = template;
    }

    public void publish(List events) {
        events.forEach(e -> {
            log.info("Sending event to {} type {}", queue.getName(), e.getClass());
            template.convertAndSend(queue.getName(), e);
        });
    }

    public void republish(List<DomainEvent> events) {

    }

    public <T extends DomainEventListener> T register(T listener) {

        return null;
    }

}
