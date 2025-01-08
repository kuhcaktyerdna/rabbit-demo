package com.demo.rabbit_demo.rabbit;

import com.demo.rabbit_demo.config.RabbitConfig;
import com.demo.rabbit_demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_STRINGS_NAME, ackMode = "MANUAL")
    public void listenForString(final String message) {
        log.info("Consumed message: {}", message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_OBJECT_NAME)
    public void listenForObjects(final User user) {
        log.info("Consumed user: {}", user);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_DLQ)
    public void listenForDlq(final Object message) {
        log.info("Consumed message in dlq: {}", message);
    }

}
