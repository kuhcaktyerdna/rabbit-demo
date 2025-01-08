package com.demo.rabbit_demo.controller;

import com.demo.rabbit_demo.model.User;
import com.demo.rabbit_demo.rabbit.RabbitProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/publish")
@RequiredArgsConstructor
public class PublishController {

    private final RabbitProducer rabbitProducer;

    @PostMapping("/string")
    public ResponseEntity<String> sendString(@RequestParam final String message) {
        log.info("Publish message: {}", message);
        rabbitProducer.sendMessage(message);
        return ResponseEntity.ok("Message published successfully");
    }

    @PostMapping("/object")
    public ResponseEntity<String> sendObject(@RequestBody final User user) {
        log.info("Publish user: {}", user);
        rabbitProducer.sendMessage(user);
        return ResponseEntity.ok("User published successfully");
    }

}
