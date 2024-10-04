package com.example.order_status_service.service;


import com.example.order_status_service.model.OrderStatusEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;
    private final String orderStatusTopic;

    public KafkaProducerService(KafkaTemplate<String, OrderStatusEvent> kafkaTemplate,
                                @Value("${app.kafka.orderStatusTopic}") String orderStatusTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderStatusTopic = orderStatusTopic;
    }

    public void sendOrderStatusEvent(OrderStatusEvent statusEvent) {
        kafkaTemplate.send(orderStatusTopic, statusEvent);
    }
}