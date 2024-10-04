package com.example.order_status_service.listener;


import com.example.order_status_service.model.OrderEvent;
import com.example.order_status_service.model.OrderStatusEvent;
import com.example.order_status_service.service.KafkaProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);
    private final KafkaProducerService kafkaProducerService;

    public OrderEventListener(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "${app.kafka.orderTopic}", groupId = "order-status-service-group")
    public void listen(ConsumerRecord<String, OrderEvent> record) {
        log.info("Received message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}",
                record.key(), record.partition(), record.topic(), record.timestamp());

        // Создание нового события со статусом
        OrderStatusEvent statusEvent = new OrderStatusEvent("CREATED", Instant.now());
        kafkaProducerService.sendOrderStatusEvent(statusEvent);
    }
}