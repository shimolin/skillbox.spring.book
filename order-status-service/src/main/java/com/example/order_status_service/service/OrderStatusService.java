package com.example.order_status_service.service;

import com.example.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderStatusService {

    @Value("${app.kafka.kafkaSentTopic}")
    private String sentTopicName;
    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void sentToKafka(Event event){
        kafkaTemplate.send(sentTopicName, event);
        log.info("------ Event: " + event.toString() + " sent to kafka");
    }
}
