package com.example.order_service.listener;

import com.example.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "${app.kafka.kafkaListenTopic}",
            groupId = "${app.kafka.kafkaOrderGroupId}",
            containerFactory = "kafkaOrderConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload Event event,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false)UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC)String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION)Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP)Long timestamp){
        log.info("------ Received event: {}", event);
        log.info("------ Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);
    }

}
