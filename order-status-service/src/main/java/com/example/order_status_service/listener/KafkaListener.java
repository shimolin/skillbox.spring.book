package com.example.order_status_service.listener;

import com.example.model.Event;
import com.example.model.Order;
import com.example.order_status_service.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListener {


    private final OrderStatusService service;

    @org.springframework.kafka.annotation.KafkaListener(topics = "${app.kafka.kafkaListenTopic}",
            groupId = "${app.kafka.kafkaOrderGroupId}",
            containerFactory = "kafkaOrderConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload Order order,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("------ Received order: {}", order);
        log.info("------ Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

        service.sentToKafka(new Event("Created", Instant.now()));

    }
}
