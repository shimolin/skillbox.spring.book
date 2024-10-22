package com.example.order_service.service;

import com.example.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    @Value("${app.kafka.kafkaSentTopic}")
    private String sentTopicName;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sentToKafka(Order order){
        kafkaTemplate.send(sentTopicName, order);
        log.info("------ Order: " + order.toString() + " sent to kafka");
    }

}
