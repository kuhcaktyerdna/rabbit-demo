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
    public static final String EXCHANGE_NAME = "ex.demo.objects";
    public static final String ROUTING_KEY_OBJECTS = "routing-key.objects.*";
    public static final String ROUTING_KEY_STRINGS = "routing-key.strings.*";

    @Bean
    public Queue objectsQueue() {
        return new Queue(QUEUE_OBJECT_NAME);
    }

    @Bean
    public Queue stringsQueue() {
        return new Queue(QUEUE_STRINGS_NAME);
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
        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();
        defaultClassMapper.setTrustedPackages("com.demo.rabbit_demo.model");
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(defaultClassMapper);
        return jackson2JsonMessageConverter;
    }

}
