package com.example.orderservice.web.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderEvent;

import com.example.orderservice.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        OrderEvent orderEvent = new OrderEvent(order.getProduct(), order.getQuantity());
        kafkaProducerService.sendOrderEvent(orderEvent);
        return "Order sent to Kafka: " + order;
    }
}