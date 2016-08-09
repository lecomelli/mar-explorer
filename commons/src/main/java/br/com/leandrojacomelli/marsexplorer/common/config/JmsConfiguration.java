package br.com.leandrojacomelli.marsexplorer.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class JmsConfiguration {

    public final static String name = "tut.hello";

    @Bean
    public Queue hello() {
        return new Queue(name);
    }

}
