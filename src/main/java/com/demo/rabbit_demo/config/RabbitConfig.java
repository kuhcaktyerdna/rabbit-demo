package com.demo.rabbit_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_OBJECT_NAME = "q.demo.objects";
    public static final String QUEUE_STRINGS_NAME = "q.demo.strings";
    public static final String QUEUE_DLQ = "q.demo.dlq";
    public static final String EXCHANGE_NAME = "ex.demo.objects";
    public static final String ROUTING_KEY_OBJECTS = "routing-key.objects.*";
    public static final String ROUTING_KEY_STRINGS = "routing-key.strings.*";

    @Bean
    public Queue objectsQueue() {
        return QueueBuilder.durable(QUEUE_OBJECT_NAME)
                .deadLetterExchange("")
                .deadLetterRoutingKey(QUEUE_DLQ)
                .ttl(5000)
                .build();
    }

    @Bean
    public Queue stringsQueue() {
        return QueueBuilder.durable(QUEUE_STRINGS_NAME)
                .deadLetterExchange("")
                .deadLetterRoutingKey(QUEUE_DLQ)
                .ttl(5000)
                .build();
    }

    @Bean
    public Queue dlqQueue() {
        return new Queue(QUEUE_DLQ);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding objectsBinding(final Queue objectsQueue, final TopicExchange exchange) {
        return BindingBuilder
                .bind(objectsQueue)
                .to(exchange)
                .with(ROUTING_KEY_OBJECTS);
    }

    @Bean
    public Binding stringsBinding(final Queue stringsQueue, final TopicExchange exchange) {
        return BindingBuilder
                .bind(stringsQueue)
                .to(exchange)
                .with(ROUTING_KEY_STRINGS);
    }

    @Bean
    public MessageConverter jsonToMapMessageConverter() {
        final DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
        defaultClassMapper.setTrustedPackages("com.demo.rabbit_demo.model");
        final Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
        return jackson2JsonMessageConverter;
    }

}
