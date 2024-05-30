package com.thecoders.cartunnbackend.orders.domain.services;

import com.thecoders.cartunnbackend.orders.domain.model.entities.Order;
import com.thecoders.cartunnbackend.orders.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderCommandService {
    private final OrderRepository orderRepository;

    public OrderCommandService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setName(orderDetails.getName());
            order.setDescription(orderDetails.getDescription());
            order.setEntryDate(orderDetails.getEntryDate());
            order.setExitDate(orderDetails.getExitDate());
            order.setStatus(orderDetails.getStatus());
            return orderRepository.save(order);
        });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
