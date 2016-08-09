package br.com.leandrojacomelli.marsexplorer.common.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CommandBus {


    private final ApplicationEventPublisher publisher;

    @Autowired
    public CommandBus(final ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void dispatch(Command command) {
        publisher.publishEvent(command);

    }


}
