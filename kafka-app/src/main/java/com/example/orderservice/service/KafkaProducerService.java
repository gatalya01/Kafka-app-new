package com.example.orderservice.service;

import com.example.orderservice.model.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String orderTopic;

    public KafkaProducerService(KafkaTemplate<String, OrderEvent> kafkaTemplate,
                                @Value("${app.kafka.orderTopic}") String orderTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderTopic = orderTopic;
    }

    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(orderTopic, orderEvent);
    }
}
