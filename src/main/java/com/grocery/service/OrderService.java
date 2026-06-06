package com.grocery.service;

import com.grocery.entity.Order;
import com.grocery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        if (order.getId() == null || order.getId().isEmpty()) {
            order.setId("ORD-" + (int)Math.floor(1000 + Math.random() * 9000));
        }
        if (order.getDate() == null) {
            order.setDate(LocalDateTime.now());
        }
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("Processing");
        }
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrderStatus(String id, String status) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setStatus(status);
                    return orderRepository.save(existingOrder);
                });
    }
}
