package com.demo.rabbit_demo.rabbit;

import com.demo.rabbit_demo.config.RabbitConfig;
import com.demo.rabbit_demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(final String message) {
        log.info("Sending message to exchange: {}", message);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY_STRINGS, message);
    }

    public void sendMessage(final User user) {
        log.info("Sending user to exchange: {}", user);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY_OBJECTS, user);
    }

}
