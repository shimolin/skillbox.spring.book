package com.example.order_service.controller;

import com.example.model.Order;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka-order-service/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/post")
    public ResponseEntity<String> postOrder(@RequestBody Order order) {
        service.sentToKafka(order);
        return ResponseEntity.ok("------ Order: " + order.toString() + " sent to kafka");
    }

}
